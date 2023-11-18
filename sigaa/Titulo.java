package sigaa;

enum Titulo {
    GRADUACAO,
    MESTRADO,
    DOUTORADO;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
