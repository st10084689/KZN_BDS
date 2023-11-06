package com.example.bds_kzn;

import java.util.ArrayList;
import java.util.List;

public class InvoicePayments {


        private String siteReference;
        private String currencyISOCode;
        private int amount;
//        private List<LineItem> lineItems;
        private String origin;
        private String createdUTCDate;
        private String originReference;
        private String reference;
        private String state;

        private String firstName;

        private String Surname;

        public InvoicePayments(String siteReference, String currencyISOCode, int amount, String origin, String createdUTCDate, String originReference, String reference, String state, String firstName, String Surname) {
            this.siteReference = siteReference;
            this.currencyISOCode = currencyISOCode;
            this.amount = amount;
            this.origin = origin;
            this.createdUTCDate = createdUTCDate;
            this.originReference = originReference;
            this.reference = reference;
            this.state = state;
            this.firstName = firstName;
            this.Surname = Surname;
        }

        public String getSiteReference() {
            return siteReference;
        }

        public void setSiteReference(String siteReference) {
            this.siteReference = siteReference;
        }

        public String getCurrencyISOCode() {
            return currencyISOCode;
        }

        public void setCurrencyISOCode(String currencyISOCode) {
            this.currencyISOCode = currencyISOCode;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getCreatedUTCDate() {
            return createdUTCDate;
        }

        public void setCreatedUTCDate(String createdUTCDate) {
            this.createdUTCDate = createdUTCDate;
        }

        public String getOriginReference() {
            return originReference;
        }

        public void setOriginReference(String originReference) {
            this.originReference = originReference;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public static class LineItem {
            private String name;
            private String productCode;
            private String SKU;
            private int unitPrice;
            private List<String> categories;
            private int quantity;

            public LineItem(String name, String productCode, String SKU, int unitPrice, List<String> categories, int quantity) {
                this.name = name;
                this.productCode = productCode;
                this.SKU = SKU;
                this.unitPrice = unitPrice;
                this.categories = categories;
                this.quantity = quantity;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public String getSKU() {
                return SKU;
            }

            public void setSKU(String SKU) {
                this.SKU = SKU;
            }

            public int getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(int unitPrice) {
                this.unitPrice = unitPrice;
            }

            public List<String> getCategories() {
                return categories;
            }

            public void setCategories(List<String> categories) {
                this.categories = categories;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }
        }
    }
