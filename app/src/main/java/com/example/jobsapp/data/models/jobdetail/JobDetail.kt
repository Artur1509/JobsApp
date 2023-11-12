package com.example.jobsapp.data.models.jobdetail

import com.example.jobsapp.data.models.Arbeitsort

data class JobDetail(

    val aktuelleVeroeffentlichungsdatum: String,
    val angebotsart: String,
    val arbeitgeber: String,
    val branchengruppe: String,
    val branche: String,
    val arbeitgeberHashId: String,
    val arbeitsorte: List<Arbeitsort>,
    val arbeitszeitmodelle: List<String>,
    val befristung: String,
    val uebernahme: Boolean,
    val betriebsgroesse: String,
    val eintrittsdatum: String,
    val ersteVeroeffentlichungsdatum: String,
    val allianzpartner: String,
    val allianzpartnerUrl: String,
    val titel: String,
    val hashId: String,
    val beruf: String,
    val modifikationsTimestamp: String,
    val stellenbeschreibung: String,
    val refnr: String,
    val fuerFluechtlingeGeeignet: Boolean,
    val nurFuerSchwerbehinderte: Boolean,
    val anzahlOffeneStellen: Int,
    //val arbeitgeberAdresse: ArbeitgeberAdresse,
    //val fertigkeiten: List<Fertigkeit>,
    //val mobilitaet: Mobilitaet,
    //val fuehrungskompetenzen: Fuehrungskompetenzen,
    val verguetung: String,
    val arbeitgeberdarstellungUrl: String,
    val arbeitgeberdarstellung: String,
    val hauptDkz: String,
    val istBetreut: Boolean,
    val istGoogleJobsRelevant: Boolean,
    val anzeigeAnonym: Boolean

)