package io.aakit.app.domain.model

import android.content.SharedPreferences
import io.kache.android.SharedPreferencesPersistent
import io.kache.core.Kache
import io.kache.core.invoke
import javax.inject.Inject

data class PersistentModel(
    val text: String = ""
) : SharedPreferencesPersistent {

    val l by lazy {  }

    fun setText(text: String) = copy(
        text = text
    )

    override fun SharedPreferences.load() = copy(
        text = getString(TEXT_KEY, text)!!
    )

    override fun SharedPreferences.Editor.save() {
        putString(TEXT_KEY, text)
    }

    private companion object {
        const val TEXT_KEY = "text"
    }

    class Cache @Inject constructor(provide: Kache.Provider) : Kache<PersistentModel> by provide()
}

