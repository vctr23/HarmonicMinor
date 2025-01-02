package com.example.harmonicminor.navScreens.home.piano

import com.example.harmonicminor.navScreens.home.InstrumentThumbnail

data class PianoThumbnail(
    override val name: String,
    override val type: String,
    override val price: String,
    override val isAvailable: Boolean,
    override val thumbNailUrl: String,
) : InstrumentThumbnail(name, type, price, isAvailable, thumbNailUrl)
