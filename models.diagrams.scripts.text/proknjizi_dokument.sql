IF EXISTS (SELECT name
   FROM   sysobjects
   WHERE  name = 'proknjizi_prometni_dokument' AND type = 'P')
   DROP PROCEDURE [proknjizi_prometni_dokument]
GO


CREATE PROCEDURE [dbo].[proknjizi_prometni_dokument]
   @PIB char(12),
   @GODINA numeric(4),
   @BROJ_PROMETNOG_DOKUMENTA numeric(5)
AS

	DECLARE
		@BROJ_TRANSAKCIJA int,
		@PORUKA varchar(100)

	IF (SELECT STATUS_DOKUMENTA
		FROM PROMETNI_DOKUMENT
		WHERE BROJ_PROMETNOG_DOKUMENTA = @BROJ_PROMETNOG_DOKUMENTA AND PIB = @PIB AND GODINA = @GODINA) <> 'F'
	BEGIN
		RAISERROR('Nije dozvoljeno knjizenje prometnog dokumenta koji nije u fazi formiranja!', 11, 2)
		RETURN
	END
	
	IF (SELECT COUNT(*)
		FROM STAVKA_PROMETNOG_DOKUMENTA
		WHERE PIB=@PIB AND GODINA=@GODINA AND BROJ_PROMETNOG_DOKUMENTA=@BROJ_PROMETNOG_DOKUMENTA)<=0
	BEGIN
		RAISERROR('Dokument mora da ima bar 1 stavku', 11, 2)
		RETURN
	END
	
	IF (SELECT ZAKLJUCENA
		FROM POSLOVNA_GODINA
		WHERE PIB = @PIB AND GODINA = @GODINA) = 1
	BEGIN
		RAISERROR('Godina ne sme biti zakljucena', 11, 2)
		RETURN
	END
	
	IF (SELECT DATUM_KNJIZENJA
		FROM PROMETNI_DOKUMENT
		WHERE PIB = @PIB AND GODINA = @GODINA AND BROJ_PROMETNOG_DOKUMENTA=@BROJ_PROMETNOG_DOKUMENTA) > GETDATE()
	BEGIN
		RAISERROR('Datum nastanka ne sme biti veci od danasnjeg', 11, 2)
		RETURN
	END
			
	DECLARE
		@C_PIB char(12),
		@C_GODINA numeric(4),
		@C_BROJ_PROMETNOG_DOKUMENTA numeric(5),
		@C_SIFRA_ARTIKLA char(4),
		@C_RBR numeric(3),
		@C_KOLICINA decimal(12,4),
		@C_CENA decimal(14,2), 
		@C_VREDNOST decimal(14,2)
					
	SET @BROJ_TRANSAKCIJA = @@TRANCOUNT
	
	IF (@BROJ_TRANSAKCIJA = 0)
	BEGIN TRANSACTION

		DECLARE cursSTAVKE CURSOR FOR
		SELECT PIB, GODINA, BROJ_PROMETNOG_DOKUMENTA, SIFRA_ARTIKLA, RBR,
			KOLICINA, CENA, VREDNOST
			FROM STAVKA_PROMETNOG_DOKUMENTA
			WHERE BROJ_PROMETNOG_DOKUMENTA = @BROJ_PROMETNOG_DOKUMENTA AND PIB=@PIB AND GODINA=@GODINA
				
		OPEN cursSTAVKE
		FETCH NEXT FROM cursSTAVKE INTO @C_PIB, @C_GODINA, @C_BROJ_PROMETNOG_DOKUMENTA, @C_SIFRA_ARTIKLA, @C_RBR,
			@C_KOLICINA, @C_CENA, @C_VREDNOST
			
		WHILE @@FETCH_STATUS=0
		BEGIN
		
			DECLARE
				@SIFRA_OBJEKTA char(12),
				@SMER int,
				@VRSTA_DOKUMENTA varchar(12),
				@UKUPNA_KOLICINA decimal(12,4)
				
			
			SET @SIFRA_OBJEKTA = (SELECT SIFRA_OBJEKTA FROM PROMETNI_DOKUMENT 
				WHERE BROJ_PROMETNOG_DOKUMENTA=@BROJ_PROMETNOG_DOKUMENTA AND PIB = @PIB AND GODINA = @GODINA)
			SET @VRSTA_DOKUMENTA = (SELECT VRSTA_DOKUMENTA FROM PROMETNI_DOKUMENT
				WHERE PIB = @C_PIB AND GODINA = @C_GODINA AND BROJ_PROMETNOG_DOKUMENTA = @C_BROJ_PROMETNOG_DOKUMENTA)
			SET @UKUPNA_KOLICINA = (SELECT KOLICINA_POCETNOG_STANJA + KOLICINA_ULAZA - KOLICINA_IZLAZA FROM MAGACINSAK_KARTICA
				WHERE PIB = @C_PIB AND SIFRA_OBJEKTA = @SIFRA_OBJEKTA AND SIFRA_ARTIKLA = @C_SIFRA_ARTIKLA AND GODINA = @C_GODINA)
			
			IF (SELECT COUNT(*)
				FROM MAGACINSAK_KARTICA
				WHERE PIB = @C_PIB AND SIFRA_OBJEKTA = @SIFRA_OBJEKTA AND SIFRA_ARTIKLA = @C_SIFRA_ARTIKLA AND GODINA = @C_GODINA) > 0
			BEGIN
				
				DECLARE
					@SUMA_CENA_ANALITIKA DECIMAL(12,4),
					@BROJ_ANALITIKA INT
					
					SET @SUMA_CENA_ANALITIKA = (SELECT SUM(CENA) FROM ANALITIKA_MAGACINSKE_KARTICE
												WHERE SIFRA_OBJEKTA = @SIFRA_OBJEKTA AND
													SIFRA_ARTIKLA = @C_SIFRA_ARTIKLA AND
													PIB = @C_PIB AND
													GODINA = @C_GODINA)
					SET @BROJ_ANALITIKA = (SELECT COUNT(*) FROM ANALITIKA_MAGACINSKE_KARTICE
												WHERE SIFRA_OBJEKTA = @SIFRA_OBJEKTA AND
													SIFRA_ARTIKLA = @C_SIFRA_ARTIKLA AND
													PIB = @C_PIB AND
													GODINA = @C_GODINA)
				
				IF @VRSTA_DOKUMENTA = 'pr'
				BEGIN
					UPDATE MAGACINSAK_KARTICA SET
						KOLICINA_ULAZA += @C_KOLICINA,
						PROSECNA_CENA = (@SUMA_CENA_ANALITIKA + @C_CENA)/(@BROJ_ANALITIKA+1),
						KALKULISANA_CENA = @C_CENA,
						VREDNOST_ULAZA = KOLICINA_ULAZA*KALKULISANA_CENA
						
					WHERE PIB = @PIB AND SIFRA_OBJEKTA = @SIFRA_OBJEKTA
						AND SIFRA_ARTIKLA = @C_SIFRA_ARTIKLA AND GODINA = @C_GODINA
				END
				IF @VRSTA_DOKUMENTA = 'ot'
				BEGIN
					IF (@UKUPNA_KOLICINA - @C_KOLICINA)<0
					BEGIN
						RAISERROR('Ukupna kolicina izlaza mora biti manja od ukupne kolicine!', 11, 2)
						RETURN
					END
				
					UPDATE MAGACINSAK_KARTICA SET
						KOLICINA_IZLAZA += @C_KOLICINA,
						PROSECNA_CENA = (@SUMA_CENA_ANALITIKA + @C_CENA)/(@BROJ_ANALITIKA+1),
						KALKULISANA_CENA = @C_CENA,
						VREDNOST_IZLAZA += KOLICINA_IZLAZA*KALKULISANA_CENA
						
					WHERE PIB = @PIB AND SIFRA_OBJEKTA = @SIFRA_OBJEKTA
						AND SIFRA_ARTIKLA = @C_SIFRA_ARTIKLA AND GODINA = @C_GODINA
				END
				
				
			END
		
			ELSE
			
			BEGIN
				IF @VRSTA_DOKUMENTA = 'pr'
				BEGIN
					INSERT INTO MAGACINSAK_KARTICA VALUES (@C_PIB, @SIFRA_OBJEKTA, @C_SIFRA_ARTIKLA, @C_GODINA, @C_CENA,
						0, 0, @C_KOLICINA, @C_VREDNOST, 0, 0, @C_CENA)
				END
				ELSE
					BEGIN
						RAISERROR('Ukupna kolicina izlaza mora biti manja od ukupne kolicine!', 11, 2)
						RETURN
					END
			END
			
			DECLARE 
				@RBR_ANALITIKE NUMERIC(5)
				
				
			SET @RBR_ANALITIKE = (SELECT COUNT(*) FROM ANALITIKA_MAGACINSKE_KARTICE
									WHERE PIB = @C_PIB AND SIFRA_OBJEKTA = @SIFRA_OBJEKTA AND
									SIFRA_ARTIKLA = @C_SIFRA_ARTIKLA AND GODINA = @C_GODINA)+1
									
			INSERT INTO ANALITIKA_MAGACINSKE_KARTICE VALUES (@RBR_ANALITIKE, @SIFRA_OBJEKTA, @C_PIB, @C_SIFRA_ARTIKLA, @C_GODINA,
				@C_BROJ_PROMETNOG_DOKUMENTA, GETDATE(), @VRSTA_DOKUMENTA,
				CONVERT(VARCHAR, @C_BROJ_PROMETNOG_DOKUMENTA) + '/' + CONVERT(VARCHAR, @C_GODINA),
				@C_KOLICINA, @C_CENA, @C_VREDNOST, CASE
					WHEN @VRSTA_DOKUMENTA = 'mm' THEN 'U'
					WHEN @VRSTA_DOKUMENTA = 'pr' THEN 'U'
					WHEN @VRSTA_DOKUMENTA = 'ot' THEN 'I'
					END)
				
			IF @@ERROR <> 0
			BEGIN
				RAISERROR('Greska pri unosu podataka u magacinske kartice i analitike', 11, 2)
				IF @BROJ_TRANSAKCIJA = 0 ROLLBACK TRANSACTION
				CLOSE cursSTAVKE
				DEALLOCATE cursSTAVKE
				RETURN
			END
			
		FETCH NEXT FROM cursSTAVKE INTO @C_PIB, @C_GODINA, @C_BROJ_PROMETNOG_DOKUMENTA, @C_SIFRA_ARTIKLA, @C_RBR,
			@C_KOLICINA, @C_CENA, @C_VREDNOST
		
		END
		
		CLOSE cursSTAVKE
		DEALLOCATE cursSTAVKE
		
		UPDATE PROMETNI_DOKUMENT SET
			STATUS_DOKUMENTA = 'p',
			DATUM_KNJIZENJA = GETDATE()  
		WHERE PIB = @PIB AND GODINA = @GODINA AND
			BROJ_PROMETNOG_DOKUMENTA = @C_BROJ_PROMETNOG_DOKUMENTA
		
		IF @@ERROR <> 0
		BEGIN
		  RAISERROR('Greska pri proknjizavanju dokumenta',11,2)
		  IF @BROJ_TRANSAKCIJA = 0 ROLLBACK TRANSACTION
		  RETURN
		END
	--	IF @BROJ_TRANSAKCIJA = 0 
		COMMIT TRANSACTION
		
