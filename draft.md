## Draft

**Current Task** - [Network Module](draft.md/#network-module)

### Main

**Idea** - Watch anime and read manga on the same app.

**Features** -
1. Integration of MAL, ie getting information on MAL.
2. Showing trending and new releases of anime across different sites.
3. Allowing users to directly rate the series on MAL.
4. Seamless watching and downloading directly in app.
5. Rich Favourite feature.
6. (Future) Translation of manga episode to anime vise versa.

### App Principles
1. Module based approach with separation of concern of each module.
2. Three-Layered Approach, ie App -> Modules -> Libraries (top to
   bottom)
3. Independent test driven development.

### Dependencies
1. **Logs** - Timber and Hyper-log(Remote Logging)
2. **Remote-Config** - Parse
3. **Lottie** - Animation
4. **Epoxy** - Recycler View
5. And all the well-know dependency glide, room, dagger, retrofit,
   RxJava, ok-http and jet-pack.

### Network Module
This module will contains all network request to different sites, ie
manga, anime and MAL(we will be using this [jikan.moe](https://jikan.moe/) ).
Anime and manga will have a default Interface for integration thorough
which all future sites can be seamlessly added.
