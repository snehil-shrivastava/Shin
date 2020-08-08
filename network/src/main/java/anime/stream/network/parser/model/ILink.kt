package anime.stream.network.parser.model

import anime.stream.core.services.models.anime.Link

data class ILink(
    override val quality: String,
    override val url: String,
    override val isEmbedded: Boolean
) : Link
