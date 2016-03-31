




echo $PATH
DB_PATH=/tmp/applifire/db/AOYWEJINZ2WHKR1SI9MEG/FB51D5CC-C671-401E-9AF2-562BB7426C62
MYSQL=/usr/bin
USER=algo
PASSWORD=algo
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'