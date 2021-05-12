package com.github.chosamuel.kishibe.webgl

import com.github.chosamuel.kishibe.application.Application

interface Buffer {
    fun bind(app: Application)
    fun unbind()
}