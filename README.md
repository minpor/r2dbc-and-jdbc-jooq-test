###### How to get started?

1. Download: https://fias-file.nalog.ru/downloads/2021.11.16/base.7z from https://fias.nalog.ru/Updates
2. Run pgdbf utils
```bash
pgdbf DOMA.DBF | iconv -c -f cp866 -t UTF-8| psql jooq_test
pgdbf FLAT.DBF | iconv -c -f cp866 -t UTF-8| psql jooq_test
pgdbf KLADR.DBF | iconv -c -f cp866 -t UTF-8| psql jooq_test
pgdbf STREET.DBF | iconv -c -f cp866 -t UTF-8| psql jooq_test
```
3. Run command
```bash 
gradle build
```
5. Run Spring Boot Application
