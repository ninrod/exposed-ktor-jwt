package org.ninrod.blog

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

class SimpleTest {
    @Test
    fun `2 + 2 = 4`() {
        val i = 4
        i `should be equal to`  2 * 2
    }
}
