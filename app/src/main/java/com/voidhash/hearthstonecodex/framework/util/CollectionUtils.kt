package com.voidhash.hearthstonecodex.framework.util

import com.voidhash.hearthstonecodex.framework.model.CollectionModel

object  CollectionUtils {

    private val collectionSet = CollectionModel.collectionSet

    fun getCollectionDrawableByName(collectionList: List<String?>?): List<Int> {
        val drawableList = mutableListOf<Int>()
        collectionList?.distinct()?.forEach { collectionName ->
            collectionSet.find { model ->
                model.name == collectionName
            }?.logo?.let { drawableList.add(it) }
        }
        return drawableList.toList()
    }

    fun getCollectionDrawables():  List<Int> {
        val drawableList = mutableListOf<Int>()
        collectionSet.forEach { model ->
            drawableList.add(model.logo)
        }
        return drawableList
    }

    fun getCollectionNames(): List<String> {
        val collectionList = mutableListOf<String>()
        collectionSet.forEach { model ->
            collectionList.add(model.name)
        }
        return collectionList.toList()
    }

    fun getCollectionNameByDrawable(drawable: Int): String? {
        return CollectionModel.collectionSet.find { model ->
            model.logo == drawable
        }?.name
    }

    fun getLogoByName(name: String): Int? {
        return collectionSet.find { model ->
            model.name == name
        }?.logo
    }

    fun getBackgroundByName(name: String): Int? {
        return collectionSet.find { model ->
            model.name == name
        }?.background
    }
}
