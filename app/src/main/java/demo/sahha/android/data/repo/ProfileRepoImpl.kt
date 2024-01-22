package demo.sahha.android.data.repo

import demo.sahha.android.domain.repo.ProfileRepo
import sdk.sahha.android.source.Sahha
import sdk.sahha.android.source.SahhaDemographic
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ProfileRepoImpl : ProfileRepo {
    override suspend fun getDemographic(): SahhaDemographic? = suspendCoroutine { cont ->
        Sahha.getDemographic { error, demographic ->
            demographic?.also { cont.resume(it) }
                ?: cont.resume(null)
        }
    }

    override suspend fun postDemographic(demographic: SahhaDemographic): Pair<String?, Boolean> =
        suspendCoroutine { cont ->
            Sahha.postDemographic(demographic) { error, success ->
                cont.resume(Pair(error, success))
            }
        }
}