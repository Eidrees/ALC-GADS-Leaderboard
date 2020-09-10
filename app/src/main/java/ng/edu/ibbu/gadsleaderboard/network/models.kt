package ng.edu.ibbu.gadsleaderboard.network

data class Learner(
    val name: String,
    val hours: Int,
    val country: String,
    val badgeUrl: String
)


data class LearnerSkillIQ(
    val name: String,
    val score: Int,
    val country: String,
    val badgeUrl: String
)