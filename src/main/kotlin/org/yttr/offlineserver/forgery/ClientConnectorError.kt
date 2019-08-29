package org.yttr.offlineserver.forgery

enum class ClientConnectorError(val message: String) {
    NETTY("io.netty.channel.AbstractChannel\$AnnotatedConnectException: Connection refused: no further information:"),
    JAVA("java.net.ConnectException: Connection refused: no further information:"),
    LEGACY("Connection refused: connect");

    override fun toString(): String {
        return this.message
    }
}