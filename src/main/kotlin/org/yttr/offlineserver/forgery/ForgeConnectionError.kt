package org.yttr.offlineserver.forgery

object ForgeConnectionError {
    // https://wiki.vg/Protocol_version_numbers

    private const val NETTY_MIN_PROTOCOL_VERSION = 320 // 17w14a
    private const val JAVA_MIN_PROTOCOL_VERSION = 0  // 13w41a

    // TODO: Find a way to handle repeated protocolVersion numbers from before rewrite
    fun getForProtocolVersion(protocolVersion: Int): ClientConnectorError = when {
        protocolVersion > NETTY_MIN_PROTOCOL_VERSION -> ClientConnectorError.NETTY
        protocolVersion > JAVA_MIN_PROTOCOL_VERSION -> ClientConnectorError.JAVA
        else -> ClientConnectorError.LEGACY
    }

    val latest = ClientConnectorError.NETTY
}