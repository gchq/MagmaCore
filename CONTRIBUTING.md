# Contributing to Magma Core

This file details the steps necessary in order to contribute to the project, providing guidance and development practices to follow in order to make successful contributions.

## Contributions

When contributing to this repository, please first discuss the change you wish to make via an issue, email, or any other method with the owners of this repository before making a change. Please read our [Code of Conduct](CODE_OF_CONDUCT.md) before contributing to this project.

Before your contributions can be accepted, you must:

- Sign the [GCHQ Contributor Licence Agreement](https://cla-assistant.io/gchq/MagmaCore).
- Push your changes to new branch.
- Submit a pull request.

## Branching Strategy

We have adopted the [Git Flow](https://guides.github.com/introduction/flow/) branching model.

### Pull Requests

Pull requests will undergo an in depth review by a project contributor to check the code changes are compliant with our coding style. This is a community so please be respectful of other members - offer encouragement, support and suggestions.

Please agree to the [GCHQ OSS Contributor License Agreement](https://cla-assistant.io/gchq/MagmaCore) before submitting a pull request.

## Issues

Where possible a pull request should correlate to a single GitHub issue. An issue should relate to a single functional or non-functional change - changes to alter/improve other pieces of functionality should be addressed in a separate issue in order to keep reviews atomic. The reasoning behind code changes should be documented in the GitHub issue.

### Workflow

- Assign yourself to the issue.
- Create a new branch off develop using pattern: `GH-[issue number]-[issue-title]`.
- Commit your changes with a suitable commit message.
- Create/update any tests as appropriate.
- Run a full build to ensure formatting checks and tests are passed.
- Update any documentation in the repository, including Javadocs.
- Create a pull request to merge your branch into develop (and assign label in-review to your issue).
- The pull request will be reviewed and following any changes and approval your branch will be merged into develop.
- Delete the branch.
- Close the issue - add a comment saying it has been merged into develop.

## Coding Style

Please ensure your coding style is consistent with rest of the project and follows our coding standards and best practices.

Checkstyle is run as part of `mvn install` so you should ensure your code is compliant with these rules. The project will not build if there are checkstyle errors.

In particular please ensure you have adhered to the following:

- Classes and methods should comply with the single responsibility principal.
- Separate out related classes into packages and avoid highly coupled classes and modules.
- Avoid magic numbers and strings literals.
- Avoid duplicating code, if necessary refactor the section of code and split it out into a reusable class.
- Make use of appropriate object oriented design patterns.
- Make use of the core Java API - don't reinvent the wheel.
- Don't expose private logic in classes through public methods.
- Field access should be controlled via getters and setters.
- Make use of generic typing.
- Consider the scope of dependencies - restrict them when possible using the appropriate maven scope.
- Use Loggers instead of System.out.print and throwable.printStackTrace.
- Ensure that toString(), equals() and hashCode() methods are implemented where appropriate.

### Javadocs

Ensure your code has sufficient Javadocs explaining what the class/method does and the intended use of it. Javadocs should be included on:

- All public classes (not required for test classes unless an explanation of the testing is required).
- public methods (not required if the functionality is obvious from the method name).
- public constants (not required if the constant is obvious from the name).

### Tests

All new code should be unit tested. Where this is not possible the code should be invoked and the functionality should be tested in an integration test. In a small number of cases this will not be possible - instead steps to verify the code should be thoroughly documented.

- Unit tests should use JUnit 4.x.
- Each test should focus on testing one small piece of functionality invoked from a single method call.
- Keep each test decoupled and don't rely on tests running in a given order - don't save state between tests.
- Tests should cover edge cases and exception cases as well as normal expected behavior.
- Unit test classes should test a single class and be named `[TestClass]Test`.
- Integration test classes should be named `[FunctionalityUnderTest]IT`.
- Overall for a given code change aim to improve the code coverage.
- Tests should be readable and self documenting where possible.
