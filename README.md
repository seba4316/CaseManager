<p align="center">
	<img src="images/logo.png" width="256">
	<h1 align="center">🔠 CaseManager 🔄</h1>
</p>


<p align="center">
	<a href="https://opensource.org/licenses/MIT">
		<img src="https://img.shields.io/github/license/seba4316/CaseManager?style=for-the-badge" /></a>
    <a href="https://github.com/seba4316/CaseManager" alt="Last Commit">
        <img src="https://img.shields.io/github/last-commit/seba4316/CaseManager?style=for-the-badge" /></a>
</p>
This **CaseManager** in Java is a versatile library that **recognizes and converts string cases** effortlessly. Whether you're working with `camelCase`, `SNAKE_CASE`, `PascalCase`, or other naming conventions, CaseManager has you covered! 🚀

## ✨ Features

- **Case Recognition**: Identify various string cases.
- **Case Conversion**: Seamlessly convert strings from one case to another.
- **Extensible**: Easily add or modify case patterns as needed.
- **Comprehensive Testing**: Robust test suites ensure reliability and correctness.

## 🌟 Supported Cases

- Title Case
- lowercase
- UPPERCASE
- camelCase
- PascalCase
- MACRO_CASE
- snake_case

## 📦 Installation

Add the following dependency to your `pom.xml` if you're using Maven:

```xml
<dependency>
    <groupId>dev.utano</groupId>
    <artifactId>CaseManager</artifactId>
    <version>1.0</version>
</dependency>
```

For Gradle, add:

```
implementation 'dev.utano:CaseManager:1.0'
```

## 🛠️ Usage

### 🎯 Recognize a String's Case

```java
public class Example {
    public static void main(String[] args) {
        String text = "hello_world";
        Case detectedCase = CaseManager.recogniseCase(text);
        System.out.println("Detected Case: " + detectedCase); // Outputs: SNAKE_CASE
    }
}
```

### 🔄 Convert a String to a Different Case

```java
public class Example {
    public static void main(String[] args) {
        String text = "helloWorld";
        String converted = CaseManager.convert(text, Case.SNAKE_CASE);
        System.out.println("Converted String: " + converted); // Outputs: hello_world
    }
}
```

### 🧩 Advanced Usage with Custom Recognisers

You can use custom implementations of `CaseRecogniser` for more control:

```java
public class CustomExample {
    public static void main(String[] args) {
        CaseRecogniser customRecogniser = new CaseRecogniserImpl(); // Or your custom implementation
        CaseConverter converter = new CaseConverterImpl(customRecogniser);
        
        String text = "HelloWorld";
        String converted = converter.convertTo(text, Case.SNAKE_CASE);
        System.out.println("Converted String: " + converted); // Outputs: hello_world
    }
}
```

## 📚 API Overview

### `CaseManager`

- **`static Case recogniseCase(String text)`**: Detects the case of the provided string.
- **`static String convert(String text, Case toCase)`**: Converts the provided string to the specified case.

### `Case` Enum

Represents various string cases with associated regex patterns and token converters.

### `AnalysedString`

Holds the result of analyzing a string, including its detected case and tokens.

### `CaseRecogniser` Interface

Defines methods for analyzing and recognizing string cases.

### `CaseConverter` Interface

Defines methods for converting strings between different cases.

## 🧪 Testing

CaseManager includes comprehensive test classes to ensure functionality:

### 🏃 Running Tests

Ensure you have JUnit set up in your project. You can run the tests using your IDE's test runner or via command line:

For Maven:

```
mvn test
```

For Gradle:

```
gradle test
```

## 🤝 Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or feature requests.

## 📄 License

This project is licensed under the [MIT License](LICENSE).