# StackOverflow Users

An Android application that fetches a list of StackOverflow users and displays it in a list on the screen.

# Functional Requirements
- When the app is launched, the user should be able to see a list of the top 20 StackOverflow users.
- Each list item should contain the user's profile image, name and reputation.
- Cells should contain an additional option to 'follow' a user. “Follow” functionality should just be locally simulated, i.e. no actual API call should be made.
- Users that are followed should show an indicator in the list item.
- Include an 'unfollow' option in the view when a user is followed.
- “Follow” status should persist between sessions. 
- If the server is unavailable (e.g. offline, error response etc), the user should see an empty state with an error message.


## How it works

- On launch the app fetches the top 20 users from the [StackExchange](https://api.stackexchange.com/2.2/users?page=1&pagesize=20&order=desc&sort=reputation&site=stackoverflow) API and displays them in a scrollable list. 
- Each card shows the user's avatar, name, role badge, badge counts, reputation and location/website.
- Users can be followed or unfollowed. 
- Follow state is persisted locally using `SharedPreferences` and survives app restarts.
- If the network request fails, an error screen is shown with a retry button.

## Architecture

**MVVM + Repository pattern**

**Dependency injection** — Hilt.

**Networking** — Retrofit + Gson. 

**Image loading** — Coil 3 with the OkHttp engine.

**Testing** — fake implementations (`FakeUsersApi`, `FakeFollowDataSource`).

## Next steps

- **Fix** — `UserType` currently holds a Compose `Color` in the domain model; the color mapping should move to the UI layer
- **Error classification** — distinguish between no-internet and server errors to show a more specific message to the user
- **Compose theming** — colors are currently defined as plain constants in `Color.kt` and referenced directly; they should be wired into a proper `MaterialTheme`
