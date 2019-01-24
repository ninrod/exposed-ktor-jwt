#!/usr/bin/env bash
# author: Filipe Silva (ninrod)
set -euo pipefail
SCRIPTPATH=$(cd $(dirname $0); pwd -P) && cd $SCRIPTPATH

pgcli postgres://postgres:example@localhost:5432
