Feature: Test CRUD methods in Discount - DiscountController

  Scenario : Discounts list appear on discount page from API

    Given the predic application discount page
    When the user push "Discounts" button on discount page
    Then discount list is appeared on discount page

  Scenario : Add new discount with submitting form

    Given the predic application add discount form
    When the user fill the discount form, push "New Discount" button
    Then new discount is added to discount list

  Scenario : Edit discount with discount edit form

    Given the predic application edit discount form
    When the user push "Edit Discount" button, fill the edit discount form
    Then selected discount is edited

  Scenario : Delete discount

    Given the predic application discount page
    And the user push "Delete Discount" button
    Then selected discount is edited

