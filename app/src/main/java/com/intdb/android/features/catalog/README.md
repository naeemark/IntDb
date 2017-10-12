## Home _`(MVP) `_ ##

An Activity to present a list of `Movies`.

#### Main Functionality ####

- Loads Multiple Carousals of movies from `API`
- Show Poster image for each item
- Pagination is implemented


#### Construction Elements / Dependencies ####

- Module to provide Dagger2 support
- Component
- Interactor
- Presenter
- XML layout

##### Presenter #####

- Provides `Interface` and implementation to manipulate UI and functionality for the Activity.
- Uses Decoupled classes for Functionality and Ui
- CarousalPresenter is responsible to manage life cycle of a single Carousal

##### Interactor #####

- Provides `interface` implementation of Use-cases, i.e. `OnFetchDataListener`


#### Propogates Changes ####

- By clicking an Item, goes to `Details Activity` 
