package com.example.kotlindemo.model

import com.example.kotlindemo.adapters.AdapterConstants
import com.example.kotlindemo.adapters.ViewType

data class Heading(val title: String) : ViewType {
    override fun getViewType() = AdapterConstants.HEADING
}