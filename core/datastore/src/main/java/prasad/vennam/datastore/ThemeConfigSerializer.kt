package prasad.vennam.datastore

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import prasad.vennam.model.ThemeConfig
import java.io.InputStream
import java.io.OutputStream

object ThemeConfigSerializer : Serializer<ThemeConfig> {
    override val defaultValue: ThemeConfig = ThemeConfig()

    override suspend fun readFrom(input: InputStream): ThemeConfig {
        return try {
            Json.decodeFromString(
                deserializer = ThemeConfig.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: ThemeConfig, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = ThemeConfig.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}
