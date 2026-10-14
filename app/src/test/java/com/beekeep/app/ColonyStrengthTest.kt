package com.beekeep.app

import org.junit.Assert.assertEquals
import org.junit.Test

class ColonyStrengthTest {
    @Test
    fun eachGradeRoundTripsThroughBackwardCompatibleStorage() {
        for (grade in 1..5) {
            assertEquals(grade, ColonyStrength.gradeFromStored(ColonyStrength.storedFromGrade(grade)))
        }
    }

    @Test
    fun labelsMatchTheFiveRequestedGrades() {
        assertEquals("Very Weak", ColonyStrength.labelForGrade(1))
        assertEquals("Weak", ColonyStrength.labelForGrade(2))
        assertEquals("Moderate", ColonyStrength.labelForGrade(3))
        assertEquals("Strong", ColonyStrength.labelForGrade(4))
        assertEquals("Very strong", ColonyStrength.labelForGrade(5))
    }

    @Test
    fun legacyZeroToTenValuesMapIntoFiveGrades() {
        assertEquals(1, ColonyStrength.gradeFromStored(0))
        assertEquals(1, ColonyStrength.gradeFromStored(2))
        assertEquals(2, ColonyStrength.gradeFromStored(3))
        assertEquals(2, ColonyStrength.gradeFromStored(4))
        assertEquals(3, ColonyStrength.gradeFromStored(5))
        assertEquals(3, ColonyStrength.gradeFromStored(6))
        assertEquals(4, ColonyStrength.gradeFromStored(7))
        assertEquals(4, ColonyStrength.gradeFromStored(8))
        assertEquals(5, ColonyStrength.gradeFromStored(9))
        assertEquals(5, ColonyStrength.gradeFromStored(10))
    }

    @Test
    fun gradeAndStorageInputsAreBounded() {
        assertEquals(2, ColonyStrength.storedFromGrade(0))
        assertEquals(10, ColonyStrength.storedFromGrade(6))
        assertEquals(1, ColonyStrength.gradeFromStored(-10))
        assertEquals(5, ColonyStrength.gradeFromStored(100))
    }
}
