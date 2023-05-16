

# Welcome to Ngikut Jetpack-Component
<p align="Center">
  <img src="https://cdn.freebiesupply.com/logos/large/2x/kotlin-1-logo-png-transparent.png" height="128"/>
  &nbsp
  &nbsp
  &nbsp
  &nbsp
  &nbsp
  <img src="https://miro.medium.com/max/1400/1*UpiyYV4onPs4emx-whdVHA.png" height="128"/>
  &nbsp
  &nbsp
  &nbsp
  &nbsp
  &nbsp
  <img src="https://tabris.com/wp-content/uploads/2021/06/jetpack-compose-icon_RGB.png" height="128"/>
<p align="center">Technologies we are using now</p>
</p>

`Ngikut Jetpack-Component` is a library that including components that might be used on most Android application. Now `Ngikut` is only available for Jetpack Compose, but gate not closed if I also make it into other environment.

# Preparation
Before you able to access `Ngikut`, you have to know that we are using `Jitpack` to make this library public. So you have to include Jitpack inside your project first.

### 1. Setup for Jitpack
-   For old version of gradle (before arctic fox update)
    
    Add this in your root  `build.gradle`  file (project scope) :
    
    ```gradle
    allprojects {
	    repositories {
		    ..
		    maven { url "https://jitpack.io" }
	    }
    }
    ```

-   For gradle version 7 or latest

    Add this in your root `setting.gradle` file :
    
    ```gradle
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            ..
            maven { url 'https://jitpack.io' }
        }
    }
    
    ```
    
### 2. Setup Ngikut to Your Dependency

-   After succesfully including Jitpack, not you can implement `Ngikut` in your project.

    Add this to your `build.gradle` (app scope) :
    ```gradle
    dependencies {
        ..
        implementation 'com.github.fahmirumagutawan:Cisu-Jetpack-Component:$cisu_version'
    }
    ```
    > -- Latest version is "..."
    > -- You can find `ngikut_version` on Jitpack Badge above, or on our release tag.

# Documentation

### 1. [Ngikut Loading Layout](https://github.com/fahmirumagutawan/Ngikut-Jetpack-Component/tree/v1.0/layout) 
- `Ngikut Loading Layout` is a composable layout to place your content that include some various loading style.

# Contact Me
If you want to discuss about business, or maybe have a critic and advice. Feel free to be messaged everytime-everywhere. 

-	#### 👨🏽‍💻 Email = `fahmigutawan@gmail.com`
-	#### 📸 [Instagram](https://www.instagram.com/fahmigutawan/)
-	#### 📚 [LinkedIn](https://www.linkedin.com/in/fahmi-noordin-rumagutawan-0b506521b/)
