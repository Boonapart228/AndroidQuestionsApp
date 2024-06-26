package com.balan.androidquestionsapp.domain.models

import com.balan.androidquestionsapp.R

enum class SortDirection(val textId : Int) {
    INCREASING(R.string.increasing),
    DECREASING(R.string.decreasing),
    NAME(R.string.name)
}