package com.voidhash.hearthstonecodex.framework.util

import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.framework.local.dao.InfoDao
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.java.KoinJavaComponent.inject

object  CollectionUtils {

    fun getCollectionDrawableByName(collectionList: List<String?>?): List<Int> {
        val drawableList = mutableListOf<Int>()
        collectionList?.distinct()?.forEach { collection->
            when(collection) {
                "TITANS" -> drawableList.add(R.drawable.titans_logo)
                "Festival of Legends" -> drawableList.add(R.drawable.festival_logo)
                "March of the Lich King" -> drawableList.add(R.drawable.lichking_logo)
                "Murder at Castle Nathria" -> drawableList.add(R.drawable.nathria_logo)
                "Voyage to the Sunken City" -> drawableList.add(R.drawable.sunken_logo)
                "Fractured in Alterac Valley" -> drawableList.add(R.drawable.altarec_logo)
                "United in Stormwind" -> drawableList.add(R.drawable.stormwind_logo)
                "Forged in the Barrens" -> drawableList.add(R.drawable.barrens_logo)
                "Madness at the Darkmoon Faire" -> drawableList.add(R.drawable.darkmoon_logo)
                "Scholomance Academy" -> drawableList.add(R.drawable.scholomance_logo)
                "Ashes of Outland" -> drawableList.add(R.drawable.ashes_logo)
                "Galakrond's Awakening" -> drawableList.add(R.drawable.galakrond_logo)
                "Descent of Dragons" -> drawableList.add(R.drawable.desscent_logo)
                "Saviors of Uldum" -> drawableList.add(R.drawable.uldum_logo)
                "Rise of Shadows" -> drawableList.add(R.drawable.shadowns_logo)
                "Rastakhan's Rumble" -> drawableList.add(R.drawable.rastakhans_logo)
                "The Boomsday Project" -> drawableList.add(R.drawable.boomsday_logo)
                "The Witchwood" -> drawableList.add(R.drawable.witchwood_logo)
                "Kobolds & Catacombs" -> drawableList.add(R.drawable.kobolds_logo)
                "Knights of the Frozen Throne" -> drawableList.add(R.drawable.knights_logo)
                "Journey to Un'Goro" -> drawableList.add(R.drawable.ungoro_logo)
                "Mean Streets of Gadgetzan" -> drawableList.add(R.drawable.gadgetzan_logo)
                "One Night in Karazhan" -> drawableList.add(R.drawable.karazhan_logo)
                "Whispers of the Old Gods" -> drawableList.add(R.drawable.whispers_logo)
                "The League of Explorers" -> drawableList.add(R.drawable.explore_logo)
                "The Grand Tournament" -> drawableList.add(R.drawable.tournament_logo)
                "Blackrock Mountain" -> drawableList.add(R.drawable.blackrock_logo)
                "Goblins vs Gnomes" -> drawableList.add(R.drawable.gvg_logo)
                "Naxxramas" -> drawableList.add(R.drawable.naxxaramas_logo)
            }
        }
        return drawableList.toList()
    }

    fun getCollectionDrawables():  List<Int> {
        val drawableList = mutableListOf<Int>()
        drawableList.add(R.drawable.titans_logo)
        drawableList.add(R.drawable.festival_logo)
        drawableList.add(R.drawable.lichking_logo)
        drawableList.add(R.drawable.nathria_logo)
        drawableList.add(R.drawable.sunken_logo)
        drawableList.add(R.drawable.altarec_logo)
        drawableList.add(R.drawable.stormwind_logo)
        drawableList.add(R.drawable.barrens_logo)
        drawableList.add(R.drawable.darkmoon_logo)
        drawableList.add(R.drawable.scholomance_logo)
        drawableList.add(R.drawable.ashes_logo)
        drawableList.add(R.drawable.galakrond_logo)
        drawableList.add(R.drawable.desscent_logo)
        drawableList.add(R.drawable.uldum_logo)
        drawableList.add(R.drawable.shadowns_logo)
        drawableList.add(R.drawable.rastakhans_logo)
        drawableList.add(R.drawable.boomsday_logo)
        drawableList.add(R.drawable.witchwood_logo)
        drawableList.add(R.drawable.kobolds_logo)
        drawableList.add(R.drawable.knights_logo)
        drawableList.add(R.drawable.ungoro_logo)
        drawableList.add(R.drawable.gadgetzan_logo)
        drawableList.add(R.drawable.karazhan_logo)
        drawableList.add(R.drawable.whispers_logo)
        drawableList.add(R.drawable.explore_logo)
        drawableList.add(R.drawable.tournament_logo)
        drawableList.add(R.drawable.blackrock_logo)
        drawableList.add(R.drawable.gvg_logo)
        drawableList.add(R.drawable.naxxaramas_logo)
        return drawableList
    }

    fun getCollectionNames(): List<String> {
        val collectionList = mutableListOf<String>()
        collectionList.add("TITANS")
        collectionList.add("Festival of Legends")
        collectionList.add("March of the Lich King")
        collectionList.add("Murder at Castle Nathria")
        collectionList.add("Voyage to the Sunken City")
        collectionList.add("Fractured in Alterac Valley")
        collectionList.add("United in Stormwind")
        collectionList.add("Forged in the Barrens")
        collectionList.add("Madness at the Darkmoon Faire")
        collectionList.add("Scholomance Academy")
        collectionList.add("Ashes of Outland")
        collectionList.add("Galakrond's Awakening")
        collectionList.add("Descent of Dragons")
        collectionList.add("Saviors of Uldum")
        collectionList.add("Rise of Shadows")
        collectionList.add("Rastakhan's Rumble")
        collectionList.add("The Boomsday Project")
        collectionList.add("The Witchwood")
        collectionList.add("Kobolds & Catacombs")
        collectionList.add("Knights of the Frozen Throne")
        collectionList.add("Journey to Un'Goro")
        collectionList.add("Mean Streets of Gadgetzan")
        collectionList.add("One Night in Karazhan")
        collectionList.add("Whispers of the Old Gods")
        collectionList.add("The League of Explorers")
        collectionList.add("The Grand Tournament")
        collectionList.add("Blackrock Mountain")
        collectionList.add("Goblins vs Gnomes")
        collectionList.add("Naxxramas")

        return collectionList.toList()
    }

    fun getCollectionNameByDrawable(drawable: Int): String {
        return when (drawable) {
            R.drawable.titans_logo -> "TITANS"
            R.drawable.festival_logo -> "Festival of Legends"
            R.drawable.lichking_logo -> "March of the Lich King"
            R.drawable.nathria_logo -> "Murder at Castle Nathria"
            R.drawable.sunken_logo ->  "Voyage to the Sunken City"
            R.drawable.altarec_logo -> "Fractured in Alterac Valley"
            R.drawable.stormwind_logo -> "United in Stormwind"
            R.drawable.barrens_logo -> "Forged in the Barrens"
            R.drawable.darkmoon_logo -> "Madness at the Darkmoon Faire"
            R.drawable.scholomance_logo -> "Scholomance Academy"
            R.drawable.ashes_logo -> "Ashes of Outland"
            R.drawable.galakrond_logo -> "Galakrond's Awakening"
            R.drawable.desscent_logo -> "Descent of Dragons"
            R.drawable.uldum_logo ->"Saviors of Uldum"
            R.drawable.shadowns_logo ->"Rise of Shadows"
            R.drawable.rastakhans_logo ->"Rastakhan's Rumble"
            R.drawable.boomsday_logo ->"The Boomsday Project"
            R.drawable.witchwood_logo ->"The Witchwood"
            R.drawable.kobolds_logo ->"Kobolds & Catacombs"
            R.drawable.knights_logo ->"Knights of the Frozen Throne"
            R.drawable.ungoro_logo ->"Journey to Un'Goro"
            R.drawable.gadgetzan_logo ->"Mean Streets of Gadgetzan"
            R.drawable.karazhan_logo ->"One Night in Karazhan"
            R.drawable.whispers_logo ->"Whispers of the Old Gods"
            R.drawable.explore_logo ->"The League of Explorers"
            R.drawable.tournament_logo ->"The Grand Tournament"
            R.drawable.blackrock_logo ->"Blackrock Mountain"
            R.drawable.gvg_logo ->"Goblins vs Gnomes"
            R.drawable.naxxaramas_logo ->"Naxxramas"
            else -> {""}
        }
    }

}
