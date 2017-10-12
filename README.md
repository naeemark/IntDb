# Int Db #

Demonstration of Requirements understanding, proposing a solution and implementation by using latest tools and techniques. The code is based on Google Android SDK.

### What is this repository for? ###

* This app is built as assignment. 
* Adds different libs to enhance the application quality and performance
* Version 1.0.0


### Application Flow ###

- A: User Clicks on App Icon for device 
- B: Splash appear
- C: After splash delay, Catalogue Screen Appears
- D: It loads Carousels, if network is available
- E: Each Carousel is independent and taking care of its own lifecycle
- F: Swiping a Carousel will fetch the next page of data
- G: By clicking a poster, it will show the details of that particular item

## How do I get set up? ##

### How to set up ###
To setup you need to clone this repo, from `master` or `develop` branch or some latest `TAG`

### Configuration ###
Please sync and resolve dependencies


## Requirements ##

- JDK 1.8
- [Android SDK](http://developer.android.com/sdk/index.html).
- Android N [(API 24) ](http://developer.android.com/tools/revisions/platforms.html).
- Latest Android SDK Tools and build tools.


## Libraries and tools included: ##

- Support libraries
- RecyclerViews and CardViews
- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Retrofit 2](http://square.github.io/retrofit/)
- [Dagger 2](http://google.github.io/dagger/)
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Timber](https://github.com/JakeWharton/timber)
- [Glide](https://github.com/bumptech/glide)
- Functional tests with [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/index.html)
- [Mockito](http://mockito.org/)
- [MVP Boilerplate](https://github.com/benoitletondor/Android-Studio-MVP-template)


## Storage ##
### Local Storage ###

- [`SharedPreferences`](https://developer.android.com/reference/android/content/SharedPreferences.html) are used for local persistance of preferences

### Remote Storage ###

- Using [`TheMovieDb-API`](https://www.themoviedb.org/documentation/api) 

## External Tools: ##

- API-response DTOs are created by using [JSON2Schema](http://www.jsonschema2pojo.org/)


## Resources ##

- [Dagger-Rx-Database-MVP](https://github.com/filippella/Dagger-Rx-Database-MVP)
- [MVP](https://hackernoon.com/yet-another-mvp-article-part-1-lets-get-to-know-the-project-d3fd553b3e21)
- [Clean Way](https://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)
- [Retrofit](http://square.github.io/retrofit/)


#### The MVP structure is based upon an Android Studio Plugin – Open Source ####
- https://github.com/benoitletondor/Android-Studio-MVP-template

## Code Quality ##

Used `Android Lint` and `SonarLint`

## Testing ##

#### Unit testing ####

- A small demonstration of Mokito is included to show, how we could test the app by JUnit

#### UI Testing ####

- A small demonstration of Esspresso testing is also provide

## Distribution ##

The project can be distributed using [Google Play Store](https://github.com/Triple-T/gradle-play-publisher).


## Contribution guidelines ##

- forks are always appreciated

## Screenshots ##

![Screenshot](screenshots/0.png)
![Screenshot](screenshots/1.png)
![Screenshot](screenshots/2.png)
![Screenshot](screenshots/3.png)
![Screenshot](screenshots/4.png)
![Screenshot](screenshots/5.png)
![Screenshot](screenshots/6.png)

![Screenshot](screenshots/7.png)

![Screenshot](screenshots/8.png)

