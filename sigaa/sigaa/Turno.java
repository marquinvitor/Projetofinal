package sigaa;

enum Turno {
    MANHA,
    TARDE,
    NOITE;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
