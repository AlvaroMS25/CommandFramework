package org.cmdfw.extras.music

import java.net.URL

/**
 * An enum representing the sources of tracks that can be used
 */
enum class Source(internal val rep: String) {
    Youtube("ytsearch:"),
    SoundCloud("scsearch:"),
    YoutubeOrLink(Youtube.rep),
    SoundCloudOrLink(SoundCloud.rep),
    Link(""),
    File(""),
}