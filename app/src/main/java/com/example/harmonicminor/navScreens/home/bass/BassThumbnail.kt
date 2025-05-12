package com.example.harmonicminor.navScreens.home.bass

import com.example.harmonicminor.navScreens.home.InstrumentThumbnail
import com.google.firebase.firestore.PropertyName

data class BassThumbnail(
    override val name: String,
    override val type: String,
    override val price: String,
    override val isAvailable: Boolean,
    @get:PropertyName("thumbNailUrl")
    override val thumbNailUrl: String,
) : InstrumentThumbnail(name, type, price, isAvailable, thumbNailUrl)