#!/bin/bash


function create_database() {
    cat ./ddl/create_database.sql \
        ./ddl/create_table_crypto.sql \
        ./ddl/create_table_user.sql \
        ./ddl/create_table_crypto_user.sql \
        ./dml/fill_table_crypto.sql \
        | mysql -u "$1" -p;
}


if [ $# -eq 2 ]
then
  if [ "$1" = "-u" ]
  then
    (create_database "$2")
  else
    echo "Invalid key" >&2
  fi
else
  echo "Wrong number of arguments" >&2
fi