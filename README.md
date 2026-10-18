# 🛒 Demo Web Shop End-to-End Selenium Automation

This project automates the complete eCommerce purchase flow on the **Demo Web Shop** application using **Java, Selenium WebDriver, TestNG, Explicit Waits, Select class, Assertions, and ChromeDriver**.

The script covers the complete flow starting from **user registration** and ending with **logout**, including product selection, cart validation, checkout, payment, order confirmation, invoice download, and final assertion.

---

## 🚀 Tech Stack

- ☕ Java
- 🌐 Selenium WebDriver
- 🧪 TestNG
- ⏳ WebDriverWait / Explicit Waits
- 🔽 Select Class
- ✅ Assertions
- 🧭 XPath
- 🎯 CSS Selectors
- 🆔 ID / Name / LinkText locators
- 🌍 Chrome Browser
- 💻 Eclipse IDE

---

## 🌐 Application Under Test

**Demo Web Shop**

```text
https://demowebshop.tricentis.com/
```

---

# 🔄 Complete Automation Flow

## 1️⃣ Launch Browser

The automation starts by launching Chrome browser in **Guest Mode**.

```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--guest");

WebDriver driver = new ChromeDriver(options);
```

The browser is then maximized.

```java
driver.manage().window().maximize();
```

An explicit wait of **30 seconds** is configured for handling dynamic elements.

```java
WebDriverWait wait = new WebDriverWait(
        driver,
        Duration.ofSeconds(30)
);
```

---

## 👤 2️⃣ User Registration

The script first navigates to the **Register** page.

```java
wait.until(
        ExpectedConditions.elementToBeClickable(
                By.cssSelector(".ico-register")
        )
).click();
```

The following registration details are entered:

- 👨 Gender
- 👤 First Name
- 👤 Last Name
- 📧 Email
- 🔐 Password
- 🔐 Confirm Password

### 📧 Dynamic Email Generation

The website does not allow registration using an already existing email address.

To avoid manually changing the email every time the script runs, a unique email address is automatically generated using:

```java
String email =
        "automation"
        + System.currentTimeMillis()
        + "@gmail.com";
```

Example:

```text
automation1792234567890@gmail.com
```

Every execution generates a different email address.

✅ This helps avoid duplicate-email registration errors.

---

## 📱 3️⃣ Navigate to Electronics

After registration, the script navigates to:

```text
Electronics
      ↓
Cell Phones
```

The Electronics category is selected using XPath.

```java
By.xpath(
    "//ul[@class='top-menu']//a[normalize-space()='Electronics']"
)
```

Then the **Cell Phones** category is opened.

---

## 🔽 4️⃣ Product Sorting

The products are sorted using Selenium's `Select` class.

The selected sorting option is:

```text
Price: Low to High
```

Example:

```java
Select select = new Select(sortBy_dropdown);

select.selectByVisibleText(
        "Price: Low to High"
);
```

---

## 🧱 5️⃣ Change Product View

The product display mode is changed to:

```text
Grid
```

using another dropdown.

```java
Select viewAs = new Select(viewAs_dropdown);

viewAs.selectByVisibleText("Grid");
```

---

## 📱 6️⃣ Select Smartphone

The script selects the product:

```text
Smartphone
```

using:

```java
driver.findElement(
        By.xpath(
            "//a[normalize-space()='Smartphone']"
        )
).click();
```

---

## 🛒 7️⃣ Add Product to Cart

The Smartphone is added to the shopping cart.

```java
wait.until(
        ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-button-43")
        )
).click();
```

---

## ✅ 8️⃣ Validate Add-to-Cart Message

After clicking **Add to Cart**, the application displays:

```text
The product has been added to your shopping cart
```

The automation captures this message:

```java
String actualMessage = message.getText();
```

Then it compares it with the expected value using TestNG:

```java
Assert.assertEquals(
        actualMessage,
        "The product has been added to your shopping cart"
);
```

If both values match:

```text
✅ Assertion Passed Successfully
```

If they do not match, TestNG throws an:

```text
❌ AssertionError
```

---

## 🛍️ 9️⃣ Open Shopping Cart

The Shopping Cart page is opened using:

```java
By.xpath(
    "//span[normalize-space()='Shopping cart']"
)
```

---

## 🚚 🔟 Estimate Shipping

The following shipping information is selected:

- 🌎 Country → United States
- 🗺️ State → Other (Non US)
- 📮 Zip Code

The script also accepts the Terms of Service checkbox.

```java
driver.findElement(
        By.id("termsofservice")
).click();
```

Then the **Checkout** button is clicked.

---

## 🧾 1️⃣1️⃣ Billing Address

The script enters the complete billing information.

The following details are entered:

- 🏢 Company
- 🌎 Country
- 🗺️ State / Province
- 🏙️ City
- 🏠 Address Line 1
- 🏠 Address Line 2
- 📮 Zip / Postal Code
- 📞 Phone Number
- 📠 Fax Number

---

## 🔄 1️⃣2️⃣ Dynamic State Dropdown Handling

The State/Province dropdown is populated dynamically after selecting the country.

Because of this, simply waiting for the dropdown to become clickable is not enough.

The script waits until the required state option appears:

```java
wait.until(
    ExpectedConditions.presenceOfElementLocated(
        By.xpath(
            "//select[@id='BillingNewAddress_StateProvinceId']" +
            "/option[normalize-space()='AA (Armed Forces Americas)']"
        )
    )
);
```

Then the state is selected:

```java
Select stateSelect =
        new Select(stateProvinceDropdown);

stateSelect.selectByVisibleText(
        "AA (Armed Forces Americas)"
);
```

✅ This prevents `NoSuchElementException`.

---

## 🚚 1️⃣3️⃣ Shipping Address

After entering billing information, the script continues to the Shipping Address section.

```java
By.xpath("//input[@onclick='Shipping.save()']")
```

The same address is used for shipping.

---

## 📦 1️⃣4️⃣ Shipping Method

The script selects the required shipping option using:

```java
By.id("shippingoption_2")
```

Then clicks the **Continue** button.

---

## 💳 1️⃣5️⃣ Payment Method

The payment method selected is:

```text
Credit Card / Visa
```

The payment method radio button is selected using:

```java
By.id("paymentmethod_2")
```

---

## 💳 1️⃣6️⃣ Payment Information

The script fills in the payment details.

The following values are provided:

- 💳 Card Type → Visa
- 👤 Cardholder Name
- 🔢 Test Card Number
- 📅 Expiry Month
- 📅 Expiry Year
- 🔐 Card Code / CVV

### 🔽 Card Type Dropdown

```java
Select cardSelect =
        new Select(
            driver.findElement(
                By.id("CreditCardType")
            )
        );

cardSelect.selectByVisibleText("Visa");
```

### 📅 Expiry Month

```java
Select expiryMonth =
        new Select(
            driver.findElement(
                By.name("ExpireMonth")
            )
        );

expiryMonth.selectByVisibleText("04");
```

### 📅 Expiry Year

```java
Select expiryYear =
        new Select(
            driver.findElement(
                By.id("ExpireYear")
            )
        );

expiryYear.selectByVisibleText("2029");
```

---

## ✅ 1️⃣7️⃣ Confirm Order

After entering all payment details, the automation clicks:

```text
Confirm Order
```

using:

```java
By.cssSelector(
    ".confirm-order-next-step-button"
)
```

---

## 🎉 1️⃣8️⃣ Order Success Validation

After successful order placement, the application displays:

```text
Your order has been successfully processed!
```

The script captures the actual message:

```java
String actualMessage1 =
        successful_Message.getText();
```

The expected message is:

```java
String expectedMessage =
        "Your order has been successfully processed!";
```

Then both values are compared:

```java
Assert.assertEquals(
        actualMessage1,
        expectedMessage
);
```

If both messages match:

```text
✅ Assertion Passed Successfully
```

This confirms that the complete purchase flow has been executed successfully.

---

## 📄 1️⃣9️⃣ Open Order Details

The script clicks:

```text
Click here for order details.
```

using:

```java
By.linkText(
    "Click here for order details."
)
```

This opens the **Order Information Page**.

---

## 📥 2️⃣0️⃣ Download PDF Invoice

On the Order Information page, the script clicks:

```text
PDF Invoice
```

using:

```java
driver.findElement(
        By.linkText("PDF Invoice")
).click();
```

This downloads the invoice generated for the order.

---

## 🔙 2️⃣1️⃣ Navigate Back

After downloading the invoice, the script returns to the previous page using:

```java
driver.navigate().back();
```

---

## ▶️ 2️⃣2️⃣ Continue from Thank You Page

Once back on the Thank You page, the script clicks the **Continue** button.

```java
wait.until(
        ExpectedConditions.elementToBeClickable(
                By.xpath(
                    "//input[@value='Continue']"
                )
        )
).click();
```

---

## 🚪 2️⃣3️⃣ Logout

Finally, the script logs out of the application.

```java
wait.until(
        ExpectedConditions.elementToBeClickable(
                By.linkText("Log out")
        )
).click();
```

---

# 🧭 Complete Test Flow

```text
🌐 Launch Browser
        ↓
👤 Register User
        ↓
📧 Generate Unique Email
        ↓
📱 Electronics
        ↓
📱 Cell Phones
        ↓
🔽 Sort Price: Low to High
        ↓
🧱 Grid View
        ↓
📱 Select Smartphone
        ↓
🛒 Add to Cart
        ↓
✅ Validate Add-to-Cart Message
        ↓
🛍️ Shopping Cart
        ↓
🚚 Shipping Estimate
        ↓
☑️ Accept Terms
        ↓
🧾 Checkout
        ↓
🏠 Billing Address
        ↓
🚚 Shipping Address
        ↓
📦 Shipping Method
        ↓
💳 Payment Method
        ↓
💳 Enter Card Details
        ↓
✅ Confirm Order
        ↓
🎉 Validate Order Success
        ↓
📄 Open Order Details
        ↓
📥 Download Invoice
        ↓
🔙 Navigate Back
        ↓
▶️ Continue
        ↓
🚪 Logout
```

---

# 🧪 Selenium Concepts Used

This project demonstrates several important Selenium automation concepts:

- ✅ Browser handling using `ChromeDriver`
- ✅ ChromeOptions
- ✅ XPath locators
- ✅ CSS selectors
- ✅ ID locators
- ✅ Name locators
- ✅ LinkText locators
- ✅ Explicit Waits
- ✅ ExpectedConditions
- ✅ WebElement handling
- ✅ Dropdown handling using `Select`
- ✅ Dynamic dropdown handling
- ✅ Dynamic test data generation
- ✅ TestNG assertions
- ✅ Browser navigation
- ✅ End-to-End eCommerce automation

---

# ⏳ Explicit Wait Example

Instead of depending heavily on fixed waits, the project uses explicit waits such as:

```java
wait.until(
        ExpectedConditions.elementToBeClickable(
                By.id("paymentmethod_2")
        )
).click();
```

This allows Selenium to wait until the required condition is satisfied before interacting with the element.

---

# 🎯 Key Learning Outcomes

Through this project, I practiced:

- 🔍 Locating different web elements
- ⏳ Handling synchronization issues
- 🔽 Working with dropdowns
- 🔄 Handling dynamically loaded dropdown options
- 📧 Creating dynamic test data
- ✅ Validating application behavior using assertions
- 🛒 Automating a complete eCommerce workflow
- 💳 Automating checkout and payment flows
- 📥 Handling invoice downloads
- 🔙 Browser navigation
- 🚪 Session logout

---

# 📂 Suggested Project Structure

```text
DemoWebShop-Selenium-Automation
│
├── src
│   └── practiceWebsite3
│       └── DemoWebShop12.java
│
├── README.md
│
├── pom.xml
│
└── testng.xml
```

> Note: `pom.xml` and `testng.xml` can be added as the project is converted into a proper Maven + TestNG automation framework.

---

# ⚙️ Prerequisites

Before running the automation, make sure you have:

- ☕ Java installed
- 🌐 Google Chrome installed
- 🧪 Selenium dependencies configured
- ✅ TestNG dependency configured
- 💻 Eclipse / IntelliJ IDEA
- 🔧 Maven recommended

---

# ▶️ How to Run

### 1. Clone the repository

```bash
git clone <your-repository-url>
```

### 2. Open the project

Open the project using:

- Eclipse
- IntelliJ IDEA

### 3. Install dependencies

Make sure Selenium and TestNG dependencies are available.

### 4. Run the Java class

Run:

```text
DemoWebShop12.java
```

as a Java application.

### 5. Observe the execution

The browser will automatically perform the complete purchase workflow.

---

# ✅ Assertions Covered

### Add-to-Cart Validation

Expected:

```text
The product has been added to your shopping cart
```

### Order Confirmation Validation

Expected:

```text
Your order has been successfully processed!
```

These assertions ensure that critical parts of the eCommerce flow are actually validated instead of only performing browser actions.

---

# 🔮 Future Enhancements

The current script is an end-to-end Selenium automation script.

Future improvements can include:

- 🧱 Page Object Model (POM)
- 🧪 Proper TestNG `@Test` methods
- 🏗️ BaseTest class
- 📊 Extent Reports / Allure Reports
- 📸 Screenshot capture on failure
- 🗃️ Test data from Excel / JSON
- ⚙️ Maven integration
- 🔄 Jenkins CI/CD integration
- 🌐 Cross-browser execution
- 🧹 Removal of remaining `Thread.sleep()`
- 📥 Automatic invoice download validation
- 🔍 Verify product details inside cart
- 💰 Validate order total
- 📦 Validate order ID
- 🔐 Move test data into configuration files

---

# 👨‍💻 Author

**Chetan Kalpande**

QA / Software Test Engineer

### Skills Demonstrated

- Manual Testing
- Selenium WebDriver
- Java
- TestNG
- API Testing
- SQL
- Functional Testing
- Regression Testing
- End-to-End Testing
- Automation Testing

---

# ⭐ Project Purpose

The purpose of this project is to demonstrate hands-on Selenium WebDriver knowledge by automating a realistic end-to-end eCommerce customer journey.

The automation validates not only UI interactions but also key business outcomes such as:

```text
✅ Successful Registration
✅ Successful Product Addition
✅ Successful Checkout
✅ Successful Payment Flow
✅ Successful Order Placement
✅ Successful Invoice Generation
✅ Successful Logout
```

⭐ If you found this project useful, feel free to star the repository.
