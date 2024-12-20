BIN_PATH=bin/kinolove/main.out
MAIN_PATH=cmd/kinolove/main.go

all: test build

build: 
	@go build -o ${BIN_PATH} ${MAIN_PATH}

test:
	@go test -v ./...
	
run:
	@chmod +x scripts/run.sh && scripts/run.sh ${BIN_PATH}

clean:
	@chmod +x scripts/clean.sh && scripts/clean.sh ${BIN_PATH}

migrate:
	@chmod +x scripts/migrations_run.sh && scripts/migrations_run.sh
