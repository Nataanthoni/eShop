#!/bin/sh

echo "Running static analysis..."

./gradlew clean test

testStatus=$?

if [ "$testStatus" = 0 ] ; then
    echo "Tests passed successfully."
else
    echo 1>&2 "Tests failed to run."
    exit 1
fi

./gradlew app:detekt --daemon

checkStatus=$?

if [ "$checkStatus" = 0 ] ; then
    echo "Static analysis found no problems."
else
    echo 1>&2 "Static analysis found violations it could not fix."
    exit 1
fi

# commit
exit 0