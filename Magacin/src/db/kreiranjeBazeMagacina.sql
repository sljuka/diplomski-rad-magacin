/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2005                    */
/* Created on:     6/17/2012 11:22:55 PM                        */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ANALITIKA_MAGACINSKE_KARTICE') and o.name = 'FK_ANALITIK_MAG_KAR_A_MAGACINS')
alter table ANALITIKA_MAGACINSKE_KARTICE
   drop constraint FK_ANALITIK_MAG_KAR_A_MAGACINS
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ANALITIKA_MAGACINSKE_KARTICE') and o.name = 'FK_ANALITIK_STAV_PRO__STAVKA_P')
alter table ANALITIKA_MAGACINSKE_KARTICE
   drop constraint FK_ANALITIK_STAV_PRO__STAVKA_P
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ARTIKAL') and o.name = 'FK_ARTIKAL_ARTIKAL_P_PREDUZEC')
alter table ARTIKAL
   drop constraint FK_ARTIKAL_ARTIKAL_P_PREDUZEC
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ARTIKAL') and o.name = 'FK_ARTIKAL_ART_MJ_MERNA_JE')
alter table ARTIKAL
   drop constraint FK_ARTIKAL_ART_MJ_MERNA_JE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('CLAN_POPISNE_KOMISIJE') and o.name = 'FK_CLAN_POP_POP_DOK_C_POPISNI_')
alter table CLAN_POPISNE_KOMISIJE
   drop constraint FK_CLAN_POP_POP_DOK_C_POPISNI_
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('CLAN_POPISNE_KOMISIJE') and o.name = 'FK_CLAN_POP_ZAP_CLN_P_ZAPOSLEN')
alter table CLAN_POPISNE_KOMISIJE
   drop constraint FK_CLAN_POP_ZAP_CLN_P_ZAPOSLEN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('MAGACINSAK_KARTICA') and o.name = 'FK_MAGACINS_ART_MAG_K_ARTIKAL')
alter table MAGACINSAK_KARTICA
   drop constraint FK_MAGACINS_ART_MAG_K_ARTIKAL
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('MAGACINSAK_KARTICA') and o.name = 'FK_MAGACINS_POS_GOD_M_POSLOVNA')
alter table MAGACINSAK_KARTICA
   drop constraint FK_MAGACINS_POS_GOD_M_POSLOVNA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('MAGACINSAK_KARTICA') and o.name = 'FK_MAGACINS_POS_OBJ_M_POSLOVNI')
alter table MAGACINSAK_KARTICA
   drop constraint FK_MAGACINS_POS_OBJ_M_POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('NASELJENO_MESTO') and o.name = 'FK_NASELJEN_DRZ_NAS_M_DRZAVA')
alter table NASELJENO_MESTO
   drop constraint FK_NASELJEN_DRZ_NAS_M_DRZAVA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POPISNI_DOKUMENT') and o.name = 'FK_POPISNI__POS_GOD_P_POSLOVNA')
alter table POPISNI_DOKUMENT
   drop constraint FK_POPISNI__POS_GOD_P_POSLOVNA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POPISNI_DOKUMENT') and o.name = 'FK_POPISNI__POS_OBJ_P_POSLOVNI')
alter table POPISNI_DOKUMENT
   drop constraint FK_POPISNI__POS_OBJ_P_POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POSEDOVANJE') and o.name = 'FK_POSEDOVA_POS_OBJ_J_POSLOVNI')
alter table POSEDOVANJE
   drop constraint FK_POSEDOVA_POS_OBJ_J_POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POSEDOVANJE') and o.name = 'FK_POSEDOVA_POS_OBJ_S_POSLOVNI')
alter table POSEDOVANJE
   drop constraint FK_POSEDOVA_POS_OBJ_S_POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POSLOVNA_GODINA') and o.name = 'FK_POSLOVNA_POS_GOD_P_PREDUZEC')
alter table POSLOVNA_GODINA
   drop constraint FK_POSLOVNA_POS_GOD_P_PREDUZEC
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POSLOVNI_OBJEKAT') and o.name = 'FK_POSLOVNI_NAS_MS_PO_NASELJEN')
alter table POSLOVNI_OBJEKAT
   drop constraint FK_POSLOVNI_NAS_MS_PO_NASELJEN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POSLOVNI_OBJEKAT') and o.name = 'FK_POSLOVNI_PRED_POS__PREDUZEC')
alter table POSLOVNI_OBJEKAT
   drop constraint FK_POSLOVNI_PRED_POS__PREDUZEC
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POSLOVNI_OBJEKAT') and o.name = 'FK_POSLOVNI_RELATIONS_TIP_OBJE')
alter table POSLOVNI_OBJEKAT
   drop constraint FK_POSLOVNI_RELATIONS_TIP_OBJE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('POSLOVNI_PARTNER') and o.name = 'FK_POSLOVNI_PRED_POSL_PREDUZEC')
alter table POSLOVNI_PARTNER
   drop constraint FK_POSLOVNI_PRED_POSL_PREDUZEC
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PREDUZECE') and o.name = 'FK_PREDUZEC_PREDUZECE_NASELJEN')
alter table PREDUZECE
   drop constraint FK_PREDUZEC_PREDUZECE_NASELJEN
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PROMETNI_DOKUMENT') and o.name = 'FK_PROMETNI_POSL_OBJ__POSLOVNI')
alter table PROMETNI_DOKUMENT
   drop constraint FK_PROMETNI_POSL_OBJ__POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PROMETNI_DOKUMENT') and o.name = 'FK_PROMETNI_POS_GOD_P_POSLOVNA')
alter table PROMETNI_DOKUMENT
   drop constraint FK_PROMETNI_POS_GOD_P_POSLOVNA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PROMETNI_DOKUMENT') and o.name = 'FK_PROMETNI_POS_OBJ_P_POSLOVNI')
alter table PROMETNI_DOKUMENT
   drop constraint FK_PROMETNI_POS_OBJ_P_POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PROMETNI_DOKUMENT') and o.name = 'FK_PROMETNI_POS_PAR_P_POSLOVNI')
alter table PROMETNI_DOKUMENT
   drop constraint FK_PROMETNI_POS_PAR_P_POSLOVNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('STAVKA_PROMETNOG_DOKUMENTA') and o.name = 'FK_STAVKA_P_ART_STAV__ARTIKAL')
alter table STAVKA_PROMETNOG_DOKUMENTA
   drop constraint FK_STAVKA_P_ART_STAV__ARTIKAL
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('STAVKA_PROMETNOG_DOKUMENTA') and o.name = 'FK_STAVKA_P_STAV_PRO__PROMETNI')
alter table STAVKA_PROMETNOG_DOKUMENTA
   drop constraint FK_STAVKA_P_STAV_PRO__PROMETNI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('STAVKE_POPISA') and o.name = 'FK_STAVKE_P_POP_DOK_S_POPISNI_')
alter table STAVKE_POPISA
   drop constraint FK_STAVKE_P_POP_DOK_S_POPISNI_
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('STAVKE_POPISA') and o.name = 'FK_STAVKE_P_STV_POP_A_ARTIKAL')
alter table STAVKE_POPISA
   drop constraint FK_STAVKE_P_STV_POP_A_ARTIKAL
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('ZAPOSLENI') and o.name = 'FK_ZAPOSLEN_ZAP_PRED_PREDUZEC')
alter table ZAPOSLENI
   drop constraint FK_ZAPOSLEN_ZAP_PRED_PREDUZEC
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ANALITIKA_MAGACINSKE_KARTICE')
            and   name  = 'STAV_PRO_DOK_ANL_MG_KAR_FK'
            and   indid > 0
            and   indid < 255)
   drop index ANALITIKA_MAGACINSKE_KARTICE.STAV_PRO_DOK_ANL_MG_KAR_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ANALITIKA_MAGACINSKE_KARTICE')
            and   name  = 'MAG_KAR_ANA_MAG_FK'
            and   indid > 0
            and   indid < 255)
   drop index ANALITIKA_MAGACINSKE_KARTICE.MAG_KAR_ANA_MAG_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ANALITIKA_MAGACINSKE_KARTICE')
            and   type = 'U')
   drop table ANALITIKA_MAGACINSKE_KARTICE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ARTIKAL')
            and   name  = 'RELATIONSHIP_27_FK'
            and   indid > 0
            and   indid < 255)
   drop index ARTIKAL.RELATIONSHIP_27_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ARTIKAL')
            and   name  = 'ART_MJ_FK'
            and   indid > 0
            and   indid < 255)
   drop index ARTIKAL.ART_MJ_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ARTIKAL')
            and   type = 'U')
   drop table ARTIKAL
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('CLAN_POPISNE_KOMISIJE')
            and   name  = 'ZAP_CLN_POP_KOM_FK'
            and   indid > 0
            and   indid < 255)
   drop index CLAN_POPISNE_KOMISIJE.ZAP_CLN_POP_KOM_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('CLAN_POPISNE_KOMISIJE')
            and   name  = 'POP_DOK_CLN_POP_KOM_FK'
            and   indid > 0
            and   indid < 255)
   drop index CLAN_POPISNE_KOMISIJE.POP_DOK_CLN_POP_KOM_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('CLAN_POPISNE_KOMISIJE')
            and   type = 'U')
   drop table CLAN_POPISNE_KOMISIJE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('DRZAVA')
            and   type = 'U')
   drop table DRZAVA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('MAGACINSAK_KARTICA')
            and   name  = 'POS_GOD_MAG_KAR_FK'
            and   indid > 0
            and   indid < 255)
   drop index MAGACINSAK_KARTICA.POS_GOD_MAG_KAR_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('MAGACINSAK_KARTICA')
            and   name  = 'ART_MAG_KAR_FK'
            and   indid > 0
            and   indid < 255)
   drop index MAGACINSAK_KARTICA.ART_MAG_KAR_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('MAGACINSAK_KARTICA')
            and   name  = 'POS_OBJ_MAG_KAR_FK'
            and   indid > 0
            and   indid < 255)
   drop index MAGACINSAK_KARTICA.POS_OBJ_MAG_KAR_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('MAGACINSAK_KARTICA')
            and   type = 'U')
   drop table MAGACINSAK_KARTICA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('MERNA_JEDINICA')
            and   type = 'U')
   drop table MERNA_JEDINICA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('NASELJENO_MESTO')
            and   name  = 'DRZ_NAS_MS_FK'
            and   indid > 0
            and   indid < 255)
   drop index NASELJENO_MESTO.DRZ_NAS_MS_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('NASELJENO_MESTO')
            and   type = 'U')
   drop table NASELJENO_MESTO
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POPISNI_DOKUMENT')
            and   name  = 'POS_OBJ_POP_DOK_FK'
            and   indid > 0
            and   indid < 255)
   drop index POPISNI_DOKUMENT.POS_OBJ_POP_DOK_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POPISNI_DOKUMENT')
            and   name  = 'POS_GOD_POP_DOK_FK'
            and   indid > 0
            and   indid < 255)
   drop index POPISNI_DOKUMENT.POS_GOD_POP_DOK_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POPISNI_DOKUMENT')
            and   type = 'U')
   drop table POPISNI_DOKUMENT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSEDOVANJE')
            and   name  = 'POS_OBJ_POSE_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSEDOVANJE.POS_OBJ_POSE_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSEDOVANJE')
            and   name  = 'POS_OBJ_POS_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSEDOVANJE.POS_OBJ_POS_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POSEDOVANJE')
            and   type = 'U')
   drop table POSEDOVANJE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNA_GODINA')
            and   name  = 'POS_GOD_PRED_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNA_GODINA.POS_GOD_PRED_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POSLOVNA_GODINA')
            and   type = 'U')
   drop table POSLOVNA_GODINA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_OBJEKAT')
            and   name  = 'RELATIONSHIP_29_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_OBJEKAT.RELATIONSHIP_29_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_OBJEKAT')
            and   name  = 'PRED_POS_OBJ_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_OBJEKAT.PRED_POS_OBJ_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_OBJEKAT')
            and   name  = 'NAS_MS_POS_OBJ_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_OBJEKAT.NAS_MS_POS_OBJ_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POSLOVNI_OBJEKAT')
            and   type = 'U')
   drop table POSLOVNI_OBJEKAT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('POSLOVNI_PARTNER')
            and   name  = 'PRED_POS_PAR_FK'
            and   indid > 0
            and   indid < 255)
   drop index POSLOVNI_PARTNER.PRED_POS_PAR_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('POSLOVNI_PARTNER')
            and   type = 'U')
   drop table POSLOVNI_PARTNER
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PREDUZECE')
            and   name  = 'RELATIONSHIP_28_FK'
            and   indid > 0
            and   indid < 255)
   drop index PREDUZECE.RELATIONSHIP_28_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PREDUZECE')
            and   type = 'U')
   drop table PREDUZECE
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROMETNI_DOKUMENT')
            and   name  = 'POS_GOD_PRO_DOK_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROMETNI_DOKUMENT.POS_GOD_PRO_DOK_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROMETNI_DOKUMENT')
            and   name  = 'POS_PAR_PRO_DOK_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROMETNI_DOKUMENT.POS_PAR_PRO_DOK_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROMETNI_DOKUMENT')
            and   name  = 'POS_OBJ_PRO_DOK1_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROMETNI_DOKUMENT.POS_OBJ_PRO_DOK1_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PROMETNI_DOKUMENT')
            and   name  = 'POS_OBJ_PRO_DOK0_FK'
            and   indid > 0
            and   indid < 255)
   drop index PROMETNI_DOKUMENT.POS_OBJ_PRO_DOK0_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PROMETNI_DOKUMENT')
            and   type = 'U')
   drop table PROMETNI_DOKUMENT
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('STAVKA_PROMETNOG_DOKUMENTA')
            and   name  = 'ART_STAV_PRO_DOK_FK'
            and   indid > 0
            and   indid < 255)
   drop index STAVKA_PROMETNOG_DOKUMENTA.ART_STAV_PRO_DOK_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('STAVKA_PROMETNOG_DOKUMENTA')
            and   type = 'U')
   drop table STAVKA_PROMETNOG_DOKUMENTA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('STAVKE_POPISA')
            and   name  = 'STV_POP_ART_FK'
            and   indid > 0
            and   indid < 255)
   drop index STAVKE_POPISA.STV_POP_ART_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('STAVKE_POPISA')
            and   name  = 'POP_DOK_STAV_POP_FK'
            and   indid > 0
            and   indid < 255)
   drop index STAVKE_POPISA.POP_DOK_STAV_POP_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('STAVKE_POPISA')
            and   type = 'U')
   drop table STAVKE_POPISA
go

if exists (select 1
            from  sysobjects
           where  id = object_id('TIP_OBJEKTA')
            and   type = 'U')
   drop table TIP_OBJEKTA
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('ZAPOSLENI')
            and   name  = 'ZAP_PRED_FK'
            and   indid > 0
            and   indid < 255)
   drop index ZAPOSLENI.ZAP_PRED_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('ZAPOSLENI')
            and   type = 'U')
   drop table ZAPOSLENI
go

/*==============================================================*/
/* Table: ANALITIKA_MAGACINSKE_KARTICE                          */
/*==============================================================*/
create table ANALITIKA_MAGACINSKE_KARTICE (
   RBR_ANALITIKE_       numeric(5)           not null,
   SIFRA_OBJEKTA        char(12)             not null,
   PIB                  char(12)             not null,
   SIFRA_ARTIKLA        char(4)              not null,
   GODINA               numeric(4)           not null,
   BROJ_PROMETNOG_DOKUMENTA numeric(5)           null,
   DATUM_PROMENE        datetime             not null,
   VR_DOK               varchar(12)          not null
      constraint CKC_VR_DOK_ANALITIK check (VR_DOK in ('pr','ot','mm','ni','kp','ps')),
   SIFRA_DOKUMENTA      char(12)             not null,
   KOLICINA             decimal(12,4)        not null,
   CENA                 decimal(14,2)        not null,
   VREDNOST             decimal(14,2)        not null,
   SMER                 varchar(12)          not null
      constraint CKC_SMER_ANALITIK check (SMER in ('U','I')),
   constraint PK_ANALITIKA_MAGACINSKE_KARTIC primary key nonclustered (SIFRA_OBJEKTA, PIB, SIFRA_ARTIKLA, GODINA, RBR_ANALITIKE_)
)
go

/*==============================================================*/
/* Index: MAG_KAR_ANA_MAG_FK                                    */
/*==============================================================*/
create index MAG_KAR_ANA_MAG_FK on ANALITIKA_MAGACINSKE_KARTICE (
SIFRA_OBJEKTA ASC,
PIB ASC,
SIFRA_ARTIKLA ASC,
GODINA ASC
)
go

/*==============================================================*/
/* Index: STAV_PRO_DOK_ANL_MG_KAR_FK                            */
/*==============================================================*/
create index STAV_PRO_DOK_ANL_MG_KAR_FK on ANALITIKA_MAGACINSKE_KARTICE (
PIB ASC,
GODINA ASC,
BROJ_PROMETNOG_DOKUMENTA ASC
)
go

/*==============================================================*/
/* Table: ARTIKAL                                               */
/*==============================================================*/
create table ARTIKAL (
   PIB                  char(12)             not null,
   SIFRA_ARTIKLA        char(4)              not null,
   SIFRA                char(5)              not null,
   NAZIV_ARTIKLA        varchar(40)          not null,
   PAKOVANJE            numeric(10)          not null,
   constraint PK_ARTIKAL primary key nonclustered (PIB, SIFRA_ARTIKLA)
)
go

/*==============================================================*/
/* Index: ART_MJ_FK                                             */
/*==============================================================*/
create index ART_MJ_FK on ARTIKAL (
SIFRA ASC
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_27_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_27_FK on ARTIKAL (
PIB ASC
)
go

/*==============================================================*/
/* Table: CLAN_POPISNE_KOMISIJE                                 */
/*==============================================================*/
create table CLAN_POPISNE_KOMISIJE (
   GODINA               numeric(4)           not null,
   PIB                  char(12)             not null,
   SIFRA_OBJEKTA        char(12)             not null,
   BROJ_POPISA          numeric(4)           not null,
   SIFRARDNK            numeric(4)           not null,
   FUNKCIJA_U_KOMISIJI  varchar(30)          not null
      constraint CKC_FUNKCIJA_U_KOMISI_CLAN_POP check (FUNKCIJA_U_KOMISIJI in ('P','C')),
   constraint PK_CLAN_POPISNE_KOMISIJE primary key (GODINA, PIB, SIFRA_OBJEKTA, BROJ_POPISA, SIFRARDNK)
)
go

/*==============================================================*/
/* Index: POP_DOK_CLN_POP_KOM_FK                                */
/*==============================================================*/
create index POP_DOK_CLN_POP_KOM_FK on CLAN_POPISNE_KOMISIJE (
GODINA ASC,
PIB ASC,
SIFRA_OBJEKTA ASC,
BROJ_POPISA ASC
)
go

/*==============================================================*/
/* Index: ZAP_CLN_POP_KOM_FK                                    */
/*==============================================================*/
create index ZAP_CLN_POP_KOM_FK on CLAN_POPISNE_KOMISIJE (
PIB ASC,
SIFRARDNK ASC
)
go

/*==============================================================*/
/* Table: DRZAVA                                                */
/*==============================================================*/
create table DRZAVA (
   SIFRA_DRZAVE         char(3)              not null,
   NAZIV_DRZAVE         varchar(40)          not null,
   constraint PK_DRZAVA primary key nonclustered (SIFRA_DRZAVE)
)
go

/*==============================================================*/
/* Table: MAGACINSAK_KARTICA                                    */
/*==============================================================*/
create table MAGACINSAK_KARTICA (
   PIB                  char(12)             not null,
   SIFRA_OBJEKTA        char(12)             not null,
   SIFRA_ARTIKLA        char(4)              not null,
   GODINA               numeric(4)           not null,
   PROSECNA_CENA        decimal(14,2)        not null,
   KOLICINA_POCETNOG_STANJA decimal(12,4)        not null,
   VREDNOST_POCETNOG_STANJA decimal(14,2)        not null,
   KOLICINA_ULAZA       decimal(12,4)        not null,
   VREDNOST_ULAZA       decimal(14,2)        not null,
   KOLICINA_IZLAZA      decimal(12,4)        not null,
   VREDNOST_IZLAZA      decimal(14,2)        not null,
   KALKULISANA_CENA     decimal(14,2)        not null,
   constraint PK_MAGACINSAK_KARTICA primary key (SIFRA_OBJEKTA, PIB, SIFRA_ARTIKLA, GODINA)
)
go

/*==============================================================*/
/* Index: POS_OBJ_MAG_KAR_FK                                    */
/*==============================================================*/
create index POS_OBJ_MAG_KAR_FK on MAGACINSAK_KARTICA (
PIB ASC,
SIFRA_OBJEKTA ASC
)
go

/*==============================================================*/
/* Index: ART_MAG_KAR_FK                                        */
/*==============================================================*/
create index ART_MAG_KAR_FK on MAGACINSAK_KARTICA (
PIB ASC,
SIFRA_ARTIKLA ASC
)
go

/*==============================================================*/
/* Index: POS_GOD_MAG_KAR_FK                                    */
/*==============================================================*/
create index POS_GOD_MAG_KAR_FK on MAGACINSAK_KARTICA (
PIB ASC,
GODINA ASC
)
go

/*==============================================================*/
/* Table: MERNA_JEDINICA                                        */
/*==============================================================*/
create table MERNA_JEDINICA (
   SIFRA                char(5)              not null,
   NAZIV                varchar(12)          not null,
   OZNAKA               varchar(5)           not null,
   constraint PK_MERNA_JEDINICA primary key nonclustered (SIFRA)
)
go

/*==============================================================*/
/* Table: NASELJENO_MESTO                                       */
/*==============================================================*/
create table NASELJENO_MESTO (
   SIFRA_DRZAVE         char(3)              not null,
   SIFRA_MESTA          char(5)              not null,
   NAZIV_MESTA          varchar(40)          not null,
   constraint PK_NASELJENO_MESTO primary key nonclustered (SIFRA_DRZAVE, SIFRA_MESTA)
)
go

/*==============================================================*/
/* Index: DRZ_NAS_MS_FK                                         */
/*==============================================================*/
create index DRZ_NAS_MS_FK on NASELJENO_MESTO (
SIFRA_DRZAVE ASC
)
go

/*==============================================================*/
/* Table: POPISNI_DOKUMENT                                      */
/*==============================================================*/
create table POPISNI_DOKUMENT (
   BROJ_POPISA          numeric(4)           not null,
   GODINA               numeric(4)           not null,
   PIB                  char(12)             not null,
   SIFRA_OBJEKTA        char(12)             not null,
   DATUM_POPISA         datetime             not null,
   STATUS_DOKUMENTA     char(1)              not null default 'f'
      constraint CKC_STATUS_DOKUMENTA_POPISNI_ check (STATUS_DOKUMENTA in ('f','p','s')),
   constraint PK_POPISNI_DOKUMENT primary key nonclustered (GODINA, PIB, SIFRA_OBJEKTA, BROJ_POPISA)
)
go

/*==============================================================*/
/* Index: POS_GOD_POP_DOK_FK                                    */
/*==============================================================*/
create index POS_GOD_POP_DOK_FK on POPISNI_DOKUMENT (
PIB ASC,
GODINA ASC
)
go

/*==============================================================*/
/* Index: POS_OBJ_POP_DOK_FK                                    */
/*==============================================================*/
create index POS_OBJ_POP_DOK_FK on POPISNI_DOKUMENT (
PIB ASC,
SIFRA_OBJEKTA ASC
)
go

/*==============================================================*/
/* Table: POSEDOVANJE                                           */
/*==============================================================*/
create table POSEDOVANJE (
   SIFRA_OBJEKTA_SADRZANOG char(12)             not null,
   SIFRA_OBJEKTA_SADRZATELJA char(12)             not null,
   PIB                  char(12)             not null,
   constraint PK_POSEDOVANJE primary key (PIB, SIFRA_OBJEKTA_SADRZATELJA, SIFRA_OBJEKTA_SADRZANOG)
)
go

/*==============================================================*/
/* Index: POS_OBJ_POS_FK                                        */
/*==============================================================*/
create index POS_OBJ_POS_FK on POSEDOVANJE (
PIB ASC,
SIFRA_OBJEKTA_SADRZATELJA ASC
)
go

/*==============================================================*/
/* Index: POS_OBJ_POSE_FK                                       */
/*==============================================================*/
create index POS_OBJ_POSE_FK on POSEDOVANJE (
PIB ASC,
SIFRA_OBJEKTA_SADRZANOG ASC
)
go

/*==============================================================*/
/* Table: POSLOVNA_GODINA                                       */
/*==============================================================*/
create table POSLOVNA_GODINA (
   PIB                  char(12)             not null,
   GODINA               numeric(4)           not null,
   ZAKLJUCENA           bit                  not null,
   constraint PK_POSLOVNA_GODINA primary key nonclustered (PIB, GODINA)
)
go

/*==============================================================*/
/* Index: POS_GOD_PRED_FK                                       */
/*==============================================================*/
create index POS_GOD_PRED_FK on POSLOVNA_GODINA (
PIB ASC
)
go

/*==============================================================*/
/* Table: POSLOVNI_OBJEKAT                                      */
/*==============================================================*/
create table POSLOVNI_OBJEKAT (
   PIB                  char(12)             not null,
   SIFRA_OBJEKTA        char(12)             not null,
   SIFRA_DRZAVE         char(3)              not null,
   SIFRA_MESTA          char(5)              not null,
   SIFRA_TIPA           char(2)              not null,
   NAZIV_OBJEKTA        varchar(40)          not null,
   constraint PK_POSLOVNI_OBJEKAT primary key nonclustered (PIB, SIFRA_OBJEKTA)
)
go

/*==============================================================*/
/* Index: NAS_MS_POS_OBJ_FK                                     */
/*==============================================================*/
create index NAS_MS_POS_OBJ_FK on POSLOVNI_OBJEKAT (
SIFRA_DRZAVE ASC,
SIFRA_MESTA ASC
)
go

/*==============================================================*/
/* Index: PRED_POS_OBJ_FK                                       */
/*==============================================================*/
create index PRED_POS_OBJ_FK on POSLOVNI_OBJEKAT (
PIB ASC
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_29_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_29_FK on POSLOVNI_OBJEKAT (
SIFRA_TIPA ASC
)
go

/*==============================================================*/
/* Table: POSLOVNI_PARTNER                                      */
/*==============================================================*/
create table POSLOVNI_PARTNER (
   PIB                  char(12)             not null,
   PIBPP                char(12)             not null,
   VRSTA                char(2)              not null
      constraint CKC_VRSTA_POSLOVNI check (VRSTA in ('k','d','kd')),
   NAZIV_PARTNERA       varchar(40)          not null,
   constraint PK_POSLOVNI_PARTNER primary key nonclustered (PIB, PIBPP)
)
go

/*==============================================================*/
/* Index: PRED_POS_PAR_FK                                       */
/*==============================================================*/
create index PRED_POS_PAR_FK on POSLOVNI_PARTNER (
PIB ASC
)
go

/*==============================================================*/
/* Table: PREDUZECE                                             */
/*==============================================================*/
create table PREDUZECE (
   PIB                  char(12)             not null,
   SIFRA_DRZAVE         char(3)              not null,
   SIFRA_MESTA          char(5)              not null,
   NAZIV_PREDUZECA      varchar(40)          not null,
   constraint PK_PREDUZECE primary key nonclustered (PIB)
)
go

/*==============================================================*/
/* Index: RELATIONSHIP_28_FK                                    */
/*==============================================================*/
create index RELATIONSHIP_28_FK on PREDUZECE (
SIFRA_DRZAVE ASC,
SIFRA_MESTA ASC
)
go

/*==============================================================*/
/* Table: PROMETNI_DOKUMENT                                     */
/*==============================================================*/
create table PROMETNI_DOKUMENT (
   PIB                  char(12)             not null,
   GODINA               numeric(4)           not null,
   BROJ_PROMETNOG_DOKUMENTA numeric(5)           not null,
   SIFRA_OBJEKTA        char(12)             not null,
   SIFRA_OBJEKTA_MM_PROMET char(12)             null,
   PIBPP                char(12)             null,
   VRSTA_DOKUMENTA      varchar(12)          not null
      constraint CKC_VRSTA_DOKUMENTA_PROMETNI check (VRSTA_DOKUMENTA in ('pr','ot','mm')),
   DATUM_NASTANKA       datetime             not null,
   DATUM_KNJIZENJA      datetime             null,
   STATUS_DOKUMENTA     char(1)              not null default 'f'
      constraint CKC_STATUS_DOKUMENTA_PROMETNI check (STATUS_DOKUMENTA in ('f','p','s')),
   constraint PK_PROMETNI_DOKUMENT primary key nonclustered (PIB, GODINA, BROJ_PROMETNOG_DOKUMENTA)
)
go

/*==============================================================*/
/* Index: POS_OBJ_PRO_DOK0_FK                                   */
/*==============================================================*/
create index POS_OBJ_PRO_DOK0_FK on PROMETNI_DOKUMENT (
PIB ASC,
SIFRA_OBJEKTA ASC
)
go

/*==============================================================*/
/* Index: POS_OBJ_PRO_DOK1_FK                                   */
/*==============================================================*/
create index POS_OBJ_PRO_DOK1_FK on PROMETNI_DOKUMENT (
PIB ASC,
SIFRA_OBJEKTA_MM_PROMET ASC
)
go

/*==============================================================*/
/* Index: POS_PAR_PRO_DOK_FK                                    */
/*==============================================================*/
create index POS_PAR_PRO_DOK_FK on PROMETNI_DOKUMENT (
PIB ASC,
PIBPP ASC
)
go

/*==============================================================*/
/* Index: POS_GOD_PRO_DOK_FK                                    */
/*==============================================================*/
create index POS_GOD_PRO_DOK_FK on PROMETNI_DOKUMENT (
PIB ASC,
GODINA ASC
)
go

/*==============================================================*/
/* Table: STAVKA_PROMETNOG_DOKUMENTA                            */
/*==============================================================*/
create table STAVKA_PROMETNOG_DOKUMENTA (
   PIB                  char(12)             not null,
   GODINA               numeric(4)           not null,
   BROJ_PROMETNOG_DOKUMENTA numeric(5)           not null,
   SIFRA_ARTIKLA        char(4)              not null,
   RBR                  numeric(3)           not null,
   KOLICINA             decimal(12,4)        not null,
   CENA                 decimal(14,2)        not null,
   VREDNOST             decimal(14,2)        null,
   constraint PK_STAVKA_PROMETNOG_DOKUMENTA primary key (PIB, GODINA, BROJ_PROMETNOG_DOKUMENTA)
)
go

/*==============================================================*/
/* Index: ART_STAV_PRO_DOK_FK                                   */
/*==============================================================*/
create index ART_STAV_PRO_DOK_FK on STAVKA_PROMETNOG_DOKUMENTA (
PIB ASC,
SIFRA_ARTIKLA ASC
)
go

/*==============================================================*/
/* Table: STAVKE_POPISA                                         */
/*==============================================================*/
create table STAVKE_POPISA (
   GODINA               numeric(4)           not null,
   SIFRA_OBJEKTA        char(12)             not null,
   BROJ_POPISA          numeric(4)           not null,
   PIB                  char(12)             not null,
   SIFRA_ARTIKLA        char(4)              not null,
   KOLICINA_PO_POPISU   decimal(12,4)        not null,
   CENA_PO_POPISU       decimal(14,2)        not null,
   VREDNOST_PO_POPISU   decimal(14,2)        null,
   KOLICINA_U_KARTICI   decimal(12,4)        not null,
   CENA_U_KARTICI       decimal(14,2)        not null,
   VREDNOST_U_KARTICI   decimal(14,2)        null,
   constraint PK_STAVKE_POPISA primary key (GODINA, SIFRA_OBJEKTA, BROJ_POPISA, PIB, SIFRA_ARTIKLA)
)
go

/*==============================================================*/
/* Index: POP_DOK_STAV_POP_FK                                   */
/*==============================================================*/
create index POP_DOK_STAV_POP_FK on STAVKE_POPISA (
GODINA ASC,
PIB ASC,
SIFRA_OBJEKTA ASC,
BROJ_POPISA ASC
)
go

/*==============================================================*/
/* Index: STV_POP_ART_FK                                        */
/*==============================================================*/
create index STV_POP_ART_FK on STAVKE_POPISA (
PIB ASC,
SIFRA_ARTIKLA ASC
)
go

/*==============================================================*/
/* Table: TIP_OBJEKTA                                           */
/*==============================================================*/
create table TIP_OBJEKTA (
   SIFRA_TIPA           char(2)              not null,
   NAZIV_TIPA           varchar(40)          not null,
   constraint PK_TIP_OBJEKTA primary key nonclustered (SIFRA_TIPA)
)
go

/*==============================================================*/
/* Table: ZAPOSLENI                                             */
/*==============================================================*/
create table ZAPOSLENI (
   PIB                  char(12)             not null,
   SIFRARDNK            numeric(4)           not null,
   PREZIME              varchar(30)          not null,
   IME                  varchar(30)          not null,
   constraint PK_ZAPOSLENI primary key nonclustered (PIB, SIFRARDNK)
)
go

/*==============================================================*/
/* Index: ZAP_PRED_FK                                           */
/*==============================================================*/
create index ZAP_PRED_FK on ZAPOSLENI (
PIB ASC
)
go

alter table ANALITIKA_MAGACINSKE_KARTICE
   add constraint FK_ANALITIK_MAG_KAR_A_MAGACINS foreign key (SIFRA_OBJEKTA, PIB, SIFRA_ARTIKLA, GODINA)
      references MAGACINSAK_KARTICA (SIFRA_OBJEKTA, PIB, SIFRA_ARTIKLA, GODINA)
go

alter table ANALITIKA_MAGACINSKE_KARTICE
   add constraint FK_ANALITIK_STAV_PRO__STAVKA_P foreign key (PIB, GODINA, BROJ_PROMETNOG_DOKUMENTA)
      references STAVKA_PROMETNOG_DOKUMENTA (PIB, GODINA, BROJ_PROMETNOG_DOKUMENTA)
go

alter table ARTIKAL
   add constraint FK_ARTIKAL_ARTIKAL_P_PREDUZEC foreign key (PIB)
      references PREDUZECE (PIB)
go

alter table ARTIKAL
   add constraint FK_ARTIKAL_ART_MJ_MERNA_JE foreign key (SIFRA)
      references MERNA_JEDINICA (SIFRA)
go

alter table CLAN_POPISNE_KOMISIJE
   add constraint FK_CLAN_POP_POP_DOK_C_POPISNI_ foreign key (GODINA, PIB, SIFRA_OBJEKTA, BROJ_POPISA)
      references POPISNI_DOKUMENT (GODINA, PIB, SIFRA_OBJEKTA, BROJ_POPISA)
go

alter table CLAN_POPISNE_KOMISIJE
   add constraint FK_CLAN_POP_ZAP_CLN_P_ZAPOSLEN foreign key (PIB, SIFRARDNK)
      references ZAPOSLENI (PIB, SIFRARDNK)
go

alter table MAGACINSAK_KARTICA
   add constraint FK_MAGACINS_ART_MAG_K_ARTIKAL foreign key (PIB, SIFRA_ARTIKLA)
      references ARTIKAL (PIB, SIFRA_ARTIKLA)
go

alter table MAGACINSAK_KARTICA
   add constraint FK_MAGACINS_POS_GOD_M_POSLOVNA foreign key (PIB, GODINA)
      references POSLOVNA_GODINA (PIB, GODINA)
go

alter table MAGACINSAK_KARTICA
   add constraint FK_MAGACINS_POS_OBJ_M_POSLOVNI foreign key (PIB, SIFRA_OBJEKTA)
      references POSLOVNI_OBJEKAT (PIB, SIFRA_OBJEKTA)
go

alter table NASELJENO_MESTO
   add constraint FK_NASELJEN_DRZ_NAS_M_DRZAVA foreign key (SIFRA_DRZAVE)
      references DRZAVA (SIFRA_DRZAVE)
go

alter table POPISNI_DOKUMENT
   add constraint FK_POPISNI__POS_GOD_P_POSLOVNA foreign key (PIB, GODINA)
      references POSLOVNA_GODINA (PIB, GODINA)
go

alter table POPISNI_DOKUMENT
   add constraint FK_POPISNI__POS_OBJ_P_POSLOVNI foreign key (PIB, SIFRA_OBJEKTA)
      references POSLOVNI_OBJEKAT (PIB, SIFRA_OBJEKTA)
go

alter table POSEDOVANJE
   add constraint FK_POSEDOVA_POS_OBJ_J_POSLOVNI foreign key (PIB, SIFRA_OBJEKTA_SADRZANOG)
      references POSLOVNI_OBJEKAT (PIB, SIFRA_OBJEKTA)
go

alter table POSEDOVANJE
   add constraint FK_POSEDOVA_POS_OBJ_S_POSLOVNI foreign key (PIB, SIFRA_OBJEKTA_SADRZATELJA)
      references POSLOVNI_OBJEKAT (PIB, SIFRA_OBJEKTA)
go

alter table POSLOVNA_GODINA
   add constraint FK_POSLOVNA_POS_GOD_P_PREDUZEC foreign key (PIB)
      references PREDUZECE (PIB)
go

alter table POSLOVNI_OBJEKAT
   add constraint FK_POSLOVNI_NAS_MS_PO_NASELJEN foreign key (SIFRA_DRZAVE, SIFRA_MESTA)
      references NASELJENO_MESTO (SIFRA_DRZAVE, SIFRA_MESTA)
go

alter table POSLOVNI_OBJEKAT
   add constraint FK_POSLOVNI_PRED_POS__PREDUZEC foreign key (PIB)
      references PREDUZECE (PIB)
go

alter table POSLOVNI_OBJEKAT
   add constraint FK_POSLOVNI_RELATIONS_TIP_OBJE foreign key (SIFRA_TIPA)
      references TIP_OBJEKTA (SIFRA_TIPA)
go

alter table POSLOVNI_PARTNER
   add constraint FK_POSLOVNI_PRED_POSL_PREDUZEC foreign key (PIB)
      references PREDUZECE (PIB)
go

alter table PREDUZECE
   add constraint FK_PREDUZEC_PREDUZECE_NASELJEN foreign key (SIFRA_DRZAVE, SIFRA_MESTA)
      references NASELJENO_MESTO (SIFRA_DRZAVE, SIFRA_MESTA)
go

alter table PROMETNI_DOKUMENT
   add constraint FK_PROMETNI_POSL_OBJ__POSLOVNI foreign key (PIB, SIFRA_OBJEKTA_MM_PROMET)
      references POSLOVNI_OBJEKAT (PIB, SIFRA_OBJEKTA)
go

alter table PROMETNI_DOKUMENT
   add constraint FK_PROMETNI_POS_GOD_P_POSLOVNA foreign key (PIB, GODINA)
      references POSLOVNA_GODINA (PIB, GODINA)
go

alter table PROMETNI_DOKUMENT
   add constraint FK_PROMETNI_POS_OBJ_P_POSLOVNI foreign key (PIB, SIFRA_OBJEKTA)
      references POSLOVNI_OBJEKAT (PIB, SIFRA_OBJEKTA)
go

alter table PROMETNI_DOKUMENT
   add constraint FK_PROMETNI_POS_PAR_P_POSLOVNI foreign key (PIB, PIBPP)
      references POSLOVNI_PARTNER (PIB, PIBPP)
go

alter table STAVKA_PROMETNOG_DOKUMENTA
   add constraint FK_STAVKA_P_ART_STAV__ARTIKAL foreign key (PIB, SIFRA_ARTIKLA)
      references ARTIKAL (PIB, SIFRA_ARTIKLA)
go

alter table STAVKA_PROMETNOG_DOKUMENTA
   add constraint FK_STAVKA_P_STAV_PRO__PROMETNI foreign key (PIB, GODINA, BROJ_PROMETNOG_DOKUMENTA)
      references PROMETNI_DOKUMENT (PIB, GODINA, BROJ_PROMETNOG_DOKUMENTA)
go

alter table STAVKE_POPISA
   add constraint FK_STAVKE_P_POP_DOK_S_POPISNI_ foreign key (GODINA, PIB, SIFRA_OBJEKTA, BROJ_POPISA)
      references POPISNI_DOKUMENT (GODINA, PIB, SIFRA_OBJEKTA, BROJ_POPISA)
go

alter table STAVKE_POPISA
   add constraint FK_STAVKE_P_STV_POP_A_ARTIKAL foreign key (PIB, SIFRA_ARTIKLA)
      references ARTIKAL (PIB, SIFRA_ARTIKLA)
go

alter table ZAPOSLENI
   add constraint FK_ZAPOSLEN_ZAP_PRED_PREDUZEC foreign key (PIB)
      references PREDUZECE (PIB)
go

