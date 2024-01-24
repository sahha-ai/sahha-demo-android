package demo.sahha.android.domain.interactor

import demo.sahha.android.data.DemoCache
import demo.sahha.android.domain.repo.ProfileRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sdk.sahha.android.source.SahhaDemographic
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ProfileInteractor @Inject constructor(
    @Named("ioScope") private val ioScope: CoroutineScope,
    private val profileRepo: ProfileRepo
) {
    suspend fun getDemographic(): SahhaDemographic? {
        return DemoCache.demographic ?: profileRepo.getDemographic()
    }

    suspend fun postDemographic(demographic: SahhaDemographic): Pair<String?, Boolean> =
        suspendCoroutine { cont ->
            ioScope.launch {
                val result = profileRepo.postDemographic(demographic)
                cacheDemographic(demographic)
                cont.resume(result)
            }
        }

    fun cacheDemographic(demographic: SahhaDemographic) {
        DemoCache.demographic = demographic
    }
}