package ng.edu.ibbu.gadsleaderboard.network

enum class ApiStatus(msg: String) {
    LOADING(msg = "loading"),
    FAILED(msg = "failed"),
    DONE(msg = "done")
}