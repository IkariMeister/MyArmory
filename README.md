# MyArmory

[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![Android CI](https://github.com/IkariMeister/MyArmory/actions/workflows/build.yml/badge.svg)](https://github.com/IkariMeister/MyArmory/actions/workflows/build.yml)
[![Android Checkstyle & Unit Testing](https://github.com/IkariMeister/MyArmory/actions/workflows/push.yml/badge.svg)](https://github.com/IkariMeister/MyArmory/actions/workflows/push.yml)

Table of contents
=================

<!--ts-->
   * [Getting Started](#getting-started)
      * [Data Layer](#data-layer)
      * [Domain Layer](#domain-layer)
      * [Presentation Layer](#presentation-layer)
      * [UI Layer](#ui-layer)
   * [CI](#ci)
<!--te-->
 

## Getting Started 
[(Top)](#MyArmory)

This repository contains an Android App to show several data about World of Warcraft Mythic Plus dungeons by using [Raider.io](https://raider.io/) and [Blizzard Battle.net](https://eu.shop.battle.net/es-es) APIs

### Data Layer
[(Top)](#MyArmory)


### Domain Layer
[(Top)](#MyArmory)

### Presentation Layer
[(Top)](#MyArmory)

### UI Layer
[(Top)](#MyArmory)

### Dependency supplying
[(Top)](#MyArmory)

For dependecy injection `Dagger Hilt` has been used because of its simplicity.

## CI 
[(Top)](#MyArmory)

* **_Android Checkstyle and Unit Test_**: this GitHub Action will check the code style on every push and pull request by using `ktlint` and `detekt`. It also run Unit tests. The report can be found as an artifact for this action. Also it generates a code coverage report with `jacoco` for unit tests.
* **_Android CI_**: this GitHub Action will assemble the debug binaries on every pull request. Those binaries can be found as binaries artifact on the action.
* **_labeler_**: GitHub Action to check on pull request, the size of the pull request categorizing them in sizes according to the number of lines modified. It will fail if the pull request has more than 1000 lines modified.
