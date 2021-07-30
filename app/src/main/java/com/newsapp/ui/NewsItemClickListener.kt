/**
 * Describes all the events originated from
 * [NewsArticlesAdapter].
 */
sealed class NewsItemClickListener {

    /* Describes item click event  */
    object newsClick : NewsItemClickListener()
}