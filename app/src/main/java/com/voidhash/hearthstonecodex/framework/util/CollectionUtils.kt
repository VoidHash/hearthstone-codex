package com.voidhash.hearthstonecodex.framework.util

import com.voidhash.hearthstonecodex.R
import com.voidhash.hearthstonecodex.framework.local.dao.InfoDao
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.java.KoinJavaComponent.inject

object  CollectionUtils {

    fun getCollectionDrawable(collectionList: List<String?>?): List<Int> {
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
}
