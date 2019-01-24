.PHONY: db backend

up:
	docker-compose up -d

down:
	docker-compose down && docker-compose rm

backend:
	docker-compose up -d chess-backend	

db:
	docker-compose up -d db

pgcli:
	./scripts/backend-connect.sh

logsdb:
	docker logs -f db

build:
	gradle build -x test

frontend-deploy:
	./scripts/frontend-deploy.sh
