package com.chip.challenge.utilities

import android.content.Intent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import com.chip.challenge.data.Breed
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf

/**
 * [Breed] objects used for tests.
 */
val testBreeds = arrayListOf(
    Breed("terrier"),
    Breed("beagle"),
    Breed("husky")
)
val testBreed = testBreeds[0]

val testBreedPhotos = arrayListOf(
    "url1",
    "url2",
    "url3"
)

/**
 * Simplify testing Intents with Chooser
 *
 * @param matcher the actual intent before wrapped by Chooser Intent
 */
fun chooser(matcher: Matcher<Intent>): Matcher<Intent> = allOf(
    hasAction(Intent.ACTION_CHOOSER),
    hasExtra(`is`(Intent.EXTRA_INTENT), matcher)
)
