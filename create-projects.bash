#!/usr/bin/env bash

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=customers-service \
--package-name=com.cardealership.customers \
--groupId=com.cardealership.customers \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
customers-service

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=employees-service \
--package-name=com.cardealership.employees \
--groupId=com.cardealership.employees \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
employees-service

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=inventory-service \
--package-name=com.cardealership.inventory \
--groupId=com.cardealership.inventory \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
inventory-service

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=purchases-service \
--package-name=com.cardealership.purchases \
--groupId=com.cardealership.purchases \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
purchases-service

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=api-gateway \
--package-name=com.cardealership.apigateway \
--groupId=com.cardealership.apigateway \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
api-gateway

