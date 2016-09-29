# _Hair Salon_

#### _September, 23rd, 2016_

#### By _**Brian Dutz**_

## Description

_A hair salon application that allows users to generate client/stylist lists and determine which stylist works with any given client by adding stylists and clients. Also includes update and delete functions_

## Setup/Installation Requirements

_Clone the repo and open it up.
In PSQL:
CREATE DATABASE hair_salon;
CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, phone varchar);
CREATE TABLE clients (id serial PRIMARY KEY, name varchar, phone varchar, stylistId int);
CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;
_

## Specifications

* Behavior: Save data
  * **Example input:** Jane Doe/John Doe
  * **Example output:** Jane Doe/John Doe
* Behavior: Correctly associate the stored information with specific details/relationships
  * **Example input:**  John Doe,Jane Doe
  * **Example output:** stylist John Doe : client Jane Doe

## Known Bugs

_None so far_

## Support and contact details

* _Any issues email me_

## Technologies Used

_Java_ and _Spark_ and _Postgres_

### License

*This software is licensed under the MIT license*

Copyright (c) 2016 **_Brian Dutz_**
