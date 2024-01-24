package demo.sahha.android.domain.repo

import sdk.sahha.android.source.SahhaDemographic

interface ProfileRepo {
    suspend fun getDemographic(): SahhaDemographic?
    suspend fun postDemographic(demographic: SahhaDemographic): Pair<String?, Boolean>
}