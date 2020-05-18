#!/bin/bash

echo "Creating Bank Account Deployment"
kubectl create -f bank-account-deployment.yaml -n bank-poc-dev

echo "Creating Bank Account Service"
kubectl apply -f bank-account-service.yaml -n bank-poc-dev

echo "Run on http://localhost:30200/api/v1/accounts"
echo "http://localhost:30200/api/v1/serverInfo"
echo "Done!"