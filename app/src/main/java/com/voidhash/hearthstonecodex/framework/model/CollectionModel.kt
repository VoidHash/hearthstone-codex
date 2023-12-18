package com.voidhash.hearthstonecodex.framework.model

import com.voidhash.hearthstonecodex.R


data class CollectionModel(var name: String, var logo: Int, var background: Int) {

    companion object {

        val collectionSet: List<CollectionModel> = listOf(
            CollectionModel("TITANS", R.drawable.titans_logo, R.drawable.titans_bg),
            CollectionModel("Festival of Legends", R.drawable.festival_logo, R.drawable.festival_bg),
            CollectionModel("March of the Lich King", R.drawable.lichking_logo, R.drawable.lichking_bg),
            CollectionModel("Murder at Castle Nathria", R.drawable.nathria_logo, R.drawable.nathria_bg),
            CollectionModel("Voyage to the Sunken City", R.drawable.sunken_logo, R.drawable.sunken_bg),
            CollectionModel("Fractured in Alterac Valley", R.drawable.altarec_logo, R.drawable.altarec_bg),
            CollectionModel("United in Stormwind", R.drawable.stormwind_logo, R.drawable.stormwind_bg),
            CollectionModel("Forged in the Barrens", R.drawable.barrens_logo, R.drawable.barrens_bg),
            CollectionModel("Madness at the Darkmoon Faire", R.drawable.darkmoon_logo, R.drawable.darkmoon_bg),
            CollectionModel("Scholomance Academy", R.drawable.scholomance_logo, R.drawable.scholomance_bg),
            CollectionModel("Ashes of Outland", R.drawable.ashes_logo, R.drawable.ashes_bg),
            CollectionModel("Galakrond's Awakening", R.drawable.galakrond_logo, R.drawable.galakrond_bg),
            CollectionModel("Descent of Dragons", R.drawable.desscent_logo, R.drawable.descent_bg),
            CollectionModel("Saviors of Uldum", R.drawable.uldum_logo, R.drawable.uldum_bg),
            CollectionModel("Rise of Shadows", R.drawable.shadowns_logo, R.drawable.shadowns_bg),
            CollectionModel("Rastakhan's Rumble", R.drawable.rastakhans_logo, R.drawable.rastakhans_bg),
            CollectionModel("The Boomsday Project", R.drawable.boomsday_logo, R.drawable.boomsday_bg),
            CollectionModel("The Witchwood", R.drawable.witchwood_logo, R.drawable.witchwood_bg),
            CollectionModel("Kobolds & Catacombs", R.drawable.kobolds_logo, R.drawable.kobolds_bg),
            CollectionModel("Knights of the Frozen Throne", R.drawable.knights_logo, R.drawable.knights_bg),
            CollectionModel("Journey to Un'Goro", R.drawable.ungoro_logo, R.drawable.ungoro_bg),
            CollectionModel("Mean Streets of Gadgetzan", R.drawable.gadgetzan_logo, R.drawable.gadgetzan_bg),
            CollectionModel("One Night in Karazhan", R.drawable.karazhan_logo, R.drawable.karazhan_bg),
            CollectionModel("Whispers of the Old Gods", R.drawable.whispers_logo, R.drawable.whispers_bg),
            CollectionModel("The League of Explorers", R.drawable.explore_logo, R.drawable.explore_bg),
            CollectionModel("The Grand Tournament", R.drawable.tournament_logo, R.drawable.tournament_bg),
            CollectionModel("Blackrock Mountain", R.drawable.blackrock_logo, R.drawable.blackrock_bg),
            CollectionModel("Goblins vs Gnomes", R.drawable.gvg_logo, R.drawable.gvg_bg),
            CollectionModel("Naxxramas", R.drawable.naxxaramas_logo, R.drawable.naxxaramas_bg),
        )
    }
}