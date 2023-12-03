package com.voidhash.hearthstonecodex.framework.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "card_table")
data class CardBase(

    @PrimaryKey
	@field:SerializedName("dbfId")
	var dbfId: Int? = null,

    @field:SerializedName("cardId")
	var cardId: String? = null,

    @field:SerializedName("name")
	var name: String? = null,

    @field:SerializedName("cardSet")
	var cardSet: String? = null,

    @field:SerializedName("type")
	var type: String? = null,

    @field:SerializedName("faction")
	var faction: String? = null,

    @field:SerializedName("rarity")
	var rarity: String? = null,

    @field:SerializedName("cost")
	var cost: Int? = null,

    @field:SerializedName("attack")
	var attack: Int? = null,

    @field:SerializedName("health")
	var health: Int? = null,

    @field:SerializedName("durability")
	var durability: Int? = null,

    @field:SerializedName("armor")
	var armor: String? = null,

    @field:SerializedName("text")
	var text: String? = null,

    @field:SerializedName("flavor")
	var flavor: String? = null,

    @field:SerializedName("artist")
	var artist: String? = null,

    @field:SerializedName("collectible")
	var collectible: Boolean? = null,

    @field:SerializedName("elite")
	var elite: Boolean? = null,

    @field:SerializedName("race")
	var race: String? = null,

    @field:SerializedName("otherRaces")
	var otherRaces: List<String?>? = null,

    @field:SerializedName("playerClass")
	var playerClass: String? = null,

    @field:SerializedName("multiClassGroup")
	var multiClassGroup: String? = null,

    @field:SerializedName("classes")
	var classes: List<String?>? = null,

    @field:SerializedName("howToGet")
	var howToGet: String? = null,

    @field:SerializedName("howToGetGold")
	var howToGetGold: String? = null,

    @field:SerializedName("howToGetSignature")
	var howToGetSignature: String? = null,

    @field:SerializedName("runeCost")
	var runeCost: RuneCost? = null,

    @field:SerializedName("img")
	var img: String? = null,

    @field:SerializedName("imgGold")
	var imgGold: String? = null,

    @field:SerializedName("locale")
	var locale: String? = null,

    @field:SerializedName("mechanics")
	var mechanics: List<MechanicsItem?>? = null
)

data class RuneCost(

	@field:SerializedName("frost")
	var frost: Int? = null,

	@field:SerializedName("unholy")
	var unholy: Int? = null,

	@field:SerializedName("blood")
	var blood: Int? = null
)

data class MechanicsItem(

	@field:SerializedName("name")
	var name: String? = null
)

data class CardModel(

	//August 2023 - Expansion
    @field:SerializedName("TITANS")
	var titans: List<CardBase?>? = null,

	//April 2023 - Expansion
    @field:SerializedName("Festival of Legends")
	var festivalOfLegends: List<CardBase?>? = null,

	//December 2022 - Expansion
    @field:SerializedName("March of the Lich King")
	var marchOfTheLichKing: List<CardBase?>? = null,

	//August 2022 - Expansion
    @field:SerializedName("Murder at Castle Nathria")
	var murderAtCastleNathria: List<CardBase?>? = null,

	//April 2022 - Expansion
    @field:SerializedName("Voyage to the Sunken City")
	var voyageToTheSunkenCity: List<CardBase?>? = null,

	//December 2021 - Expansion
    @field:SerializedName("Fractured in Alterac Valley")
	var fracturedInAlteracValley: List<CardBase?>? = null,

	//August 2021 - Expansion
    @field:SerializedName("United in Stormwind")
	var unitedInStormwind: List<CardBase?>? = null,

	//March 2021 - Expansion
    @field:SerializedName("Forged in the Barrens")
	var forgedInTheBarrens: List<CardBase?>? = null,

	//November 2020 - Expansion
    @field:SerializedName("Madness At The Darkmoon Faire")
	var madnessAtTheDarkmoonFaire: List<CardBase?>? = null,

	//August 2020 - Expansion
    @field:SerializedName("Scholomance Academy")
	var scholomanceAcademy: List<CardBase?>? = null,

	//April 2020 - Expansion
    @field:SerializedName("Ashes of Outland")
	var ashesOfOutland: List<CardBase?>? = null,

	//January 2020 - Adventure
    @field:SerializedName("Galakrond's Awakening")
	var galakrondSAwakening: List<CardBase?>? = null,

	//December 2019 - Expansion
    @field:SerializedName("Descent of Dragons")
	var descentOfDragons: List<CardBase?>? = null,

	//August 2019 - Expansion
    @field:SerializedName("Saviors of Uldum")
	var saviorsOfUldum: List<CardBase?>? = null,

	//April 2019 - Expansion
    @field:SerializedName("Rise of Shadows")
	var riseOfShadows: List<CardBase?>? = null,

	//December 2018 - Expansion
    @field:SerializedName("Rastakhan's Rumble")
	var rastakhanSRumble: List<CardBase?>? = null,

	//July 2018 - Expansion
    @field:SerializedName("The Boomsday Project")
	var theBoomsdayProject: List<CardBase?>? = null,

	//April 2018 - Expansion
    @field:SerializedName("The Witchwood")
	var theWitchwood: List<CardBase?>? = null,

	//December 2017 - Expansion
    @field:SerializedName("Kobolds & Catacombs")
	var koboldsCatacombs: List<CardBase?>? = null,

	//August 2017 - Expansion
    @field:SerializedName("Knights of the Frozen Throne")
	var knightsOfTheFrozenThrone: List<CardBase?>? = null,

	//April 2017 - Expansion
    @field:SerializedName("Journey to Un'Goro")
	var journeyToUnGoro: List<CardBase?>? = null,

	//December 2016 - Expansion
    @field:SerializedName("Mean Streets of Gadgetzan")
	var meanStreetsOfGadgetzan: List<CardBase?>? = null,

	//August 2016 - Adventure
    @field:SerializedName("One Night in Karazhan")
	var oneNightInKarazhan: List<CardBase?>? = null,

	//April 2016 - Expansion
    @field:SerializedName("Whispers of the Old Gods")
	var whispersOfTheOldGods: List<CardBase?>? = null,

	//November 2015 - Adventure
    @field:SerializedName("The League of Explorers")
	var theLeagueOfExplorers: List<CardBase?>? = null,

	//August 2015 - Expansion
    @field:SerializedName("The Grand Tournament")
	var theGrandTournament: List<CardBase?>? = null,

	//April 2015 - Adventure
    @field:SerializedName("Blackrock Mountain")
	var blackrockMountain: List<CardBase?>? = null,

	//December 2014 - Expansion
    @field:SerializedName("Goblins vs Gnomes")
	var goblinsVsGnomes: List<CardBase?>? = null,

	//June 2014 - Adventure
    @field:SerializedName("Naxxramas")
	var naxxramas: List<CardBase?>? = null,

    @field:SerializedName("Classic")
	var classic: List<CardBase?>? = null
) {
	fun getAllCards(): List<CardBase> {

		var collection = mutableListOf<CardBase>()

		titans?.forEach { card ->
			card?.let { collection.add(card) }
		}

		festivalOfLegends?.forEach { card ->
			card?.let { collection.add(card) }
		}

		marchOfTheLichKing?.forEach { card ->
			card?.let { collection.add(card) }
		}

		murderAtCastleNathria?.forEach { card ->
			card?.let { collection.add(card) }
		}

		voyageToTheSunkenCity?.forEach { card ->
			card?.let { collection.add(card) }
		}

		fracturedInAlteracValley?.forEach { card ->
			card?.let { collection.add(card) }
		}

		unitedInStormwind?.forEach { card ->
			card?.let { collection.add(card) }
		}

		forgedInTheBarrens?.forEach { card ->
			card?.let { collection.add(card) }
		}

		madnessAtTheDarkmoonFaire?.forEach { card ->
			card?.let { collection.add(card) }
		}

		scholomanceAcademy?.forEach { card ->
			card?.let { collection.add(card) }
		}

		ashesOfOutland?.forEach { card ->
			card?.let { collection.add(card) }
		}

		galakrondSAwakening?.forEach { card ->
			card?.let { collection.add(card) }
		}

		descentOfDragons?.forEach { card ->
			card?.let { collection.add(card) }
		}

		saviorsOfUldum?.forEach { card ->
			card?.let { collection.add(card) }
		}

		riseOfShadows?.forEach { card ->
			card?.let { collection.add(card) }
		}

		rastakhanSRumble?.forEach { card ->
			card?.let { collection.add(card) }
		}

		theBoomsdayProject?.forEach { card ->
			card?.let { collection.add(card) }
		}

		theWitchwood?.forEach { card ->
			card?.let { collection.add(card) }
		}

		koboldsCatacombs?.forEach { card ->
			card?.let { collection.add(card) }
		}

		knightsOfTheFrozenThrone?.forEach { card ->
			card?.let { collection.add(card) }
		}

		journeyToUnGoro?.forEach { card ->
			card?.let { collection.add(card) }
		}

		meanStreetsOfGadgetzan?.forEach { card ->
			card?.let { collection.add(card) }
		}

		oneNightInKarazhan?.forEach { card ->
			card?.let { collection.add(card) }
		}

		whispersOfTheOldGods?.forEach { card ->
			card?.let { collection.add(card) }
		}

		theLeagueOfExplorers?.forEach { card ->
			card?.let { collection.add(card) }
		}

		theGrandTournament?.forEach { card ->
			card?.let { collection.add(card) }
		}

		blackrockMountain?.forEach { card ->
			card?.let { collection.add(card) }
		}

		goblinsVsGnomes?.forEach { card ->
			card?.let { collection.add(card) }
		}

		naxxramas?.forEach { card ->
			card?.let { collection.add(card) }
		}

		classic?.forEach { card ->
			card?.let { collection.add(card) }
		}

		return collection.toList()
	}
}