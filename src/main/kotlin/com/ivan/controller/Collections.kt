package com.ivan.controller

fun <K, V> Map<K, V>.valuesAsList(): List<V> = this.map { (_, value) -> value }