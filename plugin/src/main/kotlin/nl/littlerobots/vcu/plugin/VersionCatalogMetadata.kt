/*
* Copyright 2021 Hugo Visser
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package nl.littlerobots.vcu.plugin

import nl.littlerobots.vcu.model.Comments

private val PIN_REGEX = Regex("#\\s?(?:@pin|@pinned)(?:\$|\\s.*)")
private val KEEP_REGEX = Regex("#\\s?@keep(?:\$|\\s.*)")

internal fun Comments.getPinnedKeys(): Set<String> {
    return getMatchingKeys(PIN_REGEX)
}

internal fun Comments.getKeptKeys(): Set<String> {
    return getMatchingKeys(KEEP_REGEX)
}

private fun Comments.getMatchingKeys(regex: Regex): Set<String> {
    return entryComments.filter {
        it.value.any {
            comment ->
            comment.matches(regex)
        }
    }.map {
        it.key
    }.toSet()
}
