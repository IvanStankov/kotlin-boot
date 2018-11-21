package com.ivan.util

fun <K, V> Map<K, V>.valuesAsList(): List<V> = this.map { (_, value) -> value }