package com.example.harmonicminor.navScreens.home.dj

import com.example.harmonicminor.navScreens.home.InstrumentThumbnail

data class DjThumbnail(
    override val name: String,
    override val type: String,
    override val price: String,
    override val isAvailable: Boolean,
    override val thumbNailUrl: String,
): InstrumentThumbnail(name, type, price, isAvailable, thumbNailUrl)