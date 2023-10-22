package org.cmdfw.extras.music

import java.net.URL

enum class Source(internal val rep: String) {
    Youtube("ytsearch:"),
    SoundCloud("scsearch:"),
    YoutubeOrLink(Youtube.rep),
    SoundCloudOrLink(SoundCloud.rep),
    Link(""),
    File(""),
}